$("#dataEventoFrom,#dataEventoTo").datetimepicker({
    format: 'DD/MM/YYYY',
    locale: 'it',
    useCurrent: false
});

$(".overlay").hide();
$.fn.dataTable.moment('DD/MM/YYYY');
$.fn.dataTable.moment('DD/MM/YYYY HH:mm:ss');

var dataTablePartecipazioni = $('#dataTablePartecipazioni').DataTable({
    language: {
        url: contextPath + "/resources/vendor/datatables/italian.json"
    },
    searching: false,
    autoWidth: false,
    columnDefs: [
        {
            targets: [0, 3, 4, 5, 10, 11],
            className: 'text-td-center'
        }
    ],
    fixedColumns: true
});

$(document).ready(function() {
    $("#buttonSearch").click();
});

$('#dataTablePartecipazioni').on('page.dt', function () {
    var info = dataTablePartecipazioni.page.info();
    sessionStorage.setItem("lastOpenPagePartecipazioni", info.page);
});

$('#dataTablePartecipazioni').on('draw.dt', function () {
    if (sessionStorage.getItem("lastOpenPagePartecipazioni") !== null) {
        var info = dataTablePartecipazioni.page.info();
        var page = parseInt(sessionStorage.getItem("lastOpenPagePartecipazioni"));
        if (info.page !== page) {
            sessionStorage.removeItem("lastOpenPagePartecipazioni");
            dataTablePartecipazioni.page(page).draw('page');
        }
    }
});

$("#buttonSearch").click(function() {
    blockScreen();
    resetLastSearchPartecipazioni();
    searchPartecipazioni(null);
});

$("#buttonExport").click(function() {
    exportPartecipazioni();
});

$("form#searchPartecipazioniForm :input").each(function(){
    var input = $(this);
    input.on('keypress', function(e){
        if (e.keyCode === 13){
            e.keyCode = 188;
            e.preventDefault();
            blockScreen();
            resetLastSearchPartecipazioni();
            searchPartecipazioni(null);
        }
    });
});

function resetLastSearchPartecipazioni() {
    sessionStorage.removeItem("lastOpenPagePartecipazioni");
}

function getPartecipazioniFormParams() {
    return {
        nome: $("#nome").val().trim(),
        cognome: $("#cognome").val().trim(),
        email: $("#email").val().trim(),
        coopId: $("#coopId").val(),
        dataEventoFrom: $("#dataEventoFrom").val().trim(),
        dataEventoTo: $("#dataEventoTo").val().trim(),
        tipologiaEvento: $("#tipologiaEvento").val()
    };
}

function searchPartecipazioni(latestSearchData, onCompleted) {
    
    if (latestSearchData !== null) {
        unblockScreen();
        partecipazioniRenderTable(dataTablePartecipazioni, latestSearchData, null, null);
        if (typeof onCompleted === "function") {
            onCompleted();
        }
    } else {
        $.ajax({
            type: "POST",
            url: contextPath + "/rest/partecipazioni/searchPartecipazioni",
            data: getPartecipazioniFormParams(),
            success: function(data, message, response) {
                unblockScreen();
                partecipazioniRenderTable(dataTablePartecipazioni, data, message, response);
                if (typeof onCompleted === "function") {
                    onCompleted();
                }
            }
        });
    }
}

function partecipazioniRenderTable(dataTable, data, message, response) {
    if (response !== null && response.responseText.includes("html")) {
        goToUrl(contextPath + "/login");
    } else {
        dataTable.clear();
        $(".alert").alert('close');
        if (data.header.result === 'OK') {
            if (!jQuery.isEmptyObject(data.payload) && data.payload.partecipazioniList.length > 0) {
                $.each(data.payload.partecipazioniList, function(ind, obj){
                    dataTable.row.add([
                        obj.id,
                        obj.userId,
                        obj.email,
                        obj.coopId,
                        obj.tesseraSocio == null || obj.tesseraSocio === "" ? "" : obj.tesseraSocio,
                        obj.dataEvento,
                        obj.luogoEvento,
                        obj.tipologiaEvento,
                        obj.nome,
                        obj.cognome,
                        obj.dataInserimento,
                        getIconWithConfirmAndAction(
                            "Eliminazione interesse",
                            "Sei sicuro di voler eliminare la riga interesse ID: " + obj.id + "?",
                            "deletePartecipazione('" + obj.id + "')",
                            "trash",
                            "Elimina"
                        )
                    ]);
                });
            } else {
                infoDialog.show('Info', 'Nessun risultato trovato', 'OK', '');
            }
        } else {
            showAlert('Errore: ' + data.header.errorMessage, 'danger', '#alert-container');
        }
        dataTable.draw();
    }
}

function exportPartecipazioni() {
    blockScreen();
    
    submitExportExcel(contextPath + "/rest/partecipazioni/exportPartecipazioni", getPartecipazioniFormParams());
    resetExportCompletedCookie();
    var counter = 0;
    var i = setInterval(function(){
        if (getCookie("ExportCompleted") === "1") {
            unblockScreen();
            clearInterval(i);
        } else {
            counter++;
            if (counter === 60) {
                clearInterval(i);
                unblockScreen();
            }
        }
    }, 5000);
}

function deletePartecipazione(id) {
    blockScreen();
    
    $.ajax({
        type: "POST",
        url: contextPath + "/rest/partecipazioni/deletePartecipazione",
        data: {
            id: id
        },
        success: function(data) {
            if (data.common && data.common.ack === "OK") {
                resetLastSearchPartecipazioni();
                searchPartecipazioni(null, function() {
                    showAlert("Interesse eliminato correttamente", "success", "#alert-container");
                });
            } else {
                unblockScreen();
                showAlert("Errore: " + (data.common && data.common.faultString ? data.common.faultString : "Errore durante l'eliminazione"), "danger", "#alert-container");
            }
        },
        error: function() {
            unblockScreen();
            showAlert("Errore durante l'eliminazione", "danger", "#alert-container");
        }
    });
}
