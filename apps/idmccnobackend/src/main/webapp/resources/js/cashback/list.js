$(document).ready(function() {
    $("#buttonSearch").click();
});

$(".overlay").hide();

$.fn.dataTable.moment('DD/MM/YYYY');

var dataTableCashback = $('#dataTableCashback').DataTable({
    language: {
        url: contextPath + "/resources/vendor/datatables/italian.json"
    },
    searching: false,
    autoWidth: false,
    columnDefs: [
        {
            targets: 0, 
            orderable: false,
            className: 'text-td-center'
        },
        {
            targets: 4, 
            className: 'text-td-center'
        },
        {
            targets: 5, 
            className: 'text-td-center'
        }
    ],    
    fixedColumns: true
});

$('#dataTableCashback').on('page.dt', function () {
    var info = dataTableCashback.page.info();
    sessionStorage.setItem("lastOpenPage", info.page);
});

$('#dataTableCashback').on('draw.dt', function () {
    if (sessionStorage.getItem("lastOpenPage") !== null) {
        var info = dataTableCashback.page.info();
        var page = parseInt(sessionStorage.getItem("lastOpenPage"));
        if (info.page !== page) {
            sessionStorage.removeItem("lastOpenPage");
            dataTableCashback.page(page).draw('page');
        }
    }
});

if (sessionStorage.getItem("recoverLastSearch") !== null) {
    var id = sessionStorage.getItem("id");
    var statoCashback = sessionStorage.getItem("statoCashback");
    var titolo = sessionStorage.getItem("titolo");

    if (isValid(id) === 0) {
        $("#id").val(id);
    }
    if (isValid(statoCashback) === 0) {
        $("#statoCashback").val(statoCashback);
    }
    if (isValid(titolo) === 0) {
        $("#titolo").val(titolo);
    }

    blockScreen();
    searchCashback(JSON.parse(sessionStorage.getItem("latestSearchData")));
}

$("#buttonSearch").click(function() {
    blockScreen();
    resetLastSearch();
    searchCashback(null);
});

$("form#searchCashbackForm :input").each(function(){
    var input = $(this);
    input.on('keypress', function(e){
        if (e.keyCode === 13){
            e.keyCode = 188;
            e.preventDefault();
            blockScreen();
            resetLastSearch();
            searchCashback(null);
        };
    });
});

function resetLastSearch() {
    sessionStorage.removeItem("recoverLastSearch");
    sessionStorage.removeItem("lastOpenPage");
}

function searchCashback(latestSearchData) {
    blockScreen();
    
    var id = $("#id").val().trim();
    var titolo = $("#titolo").val().trim();
    var statoCashback = $("#statoCashback").val();
    sessionStorage.setItem("id", id);
    sessionStorage.setItem("titolo", titolo);
    sessionStorage.setItem("statoCashback", statoCashback);
    sessionStorage.setItem("recoverLastSearch", "1");

    if (latestSearchData !== null) {
        unblockScreen();
        cashbackRenderTable(dataTableCashback, latestSearchData, null, null);
    } else {
        $.ajax({
            type: "POST",
            url: contextPath + "/rest/cashback/searchCashback",
            data: {
                id: id,
                titolo: titolo,
                statoCashback: statoCashback
            },
            success: function(data, message, response) {
                unblockScreen();
                cashbackRenderTable(dataTableCashback, data);
                showNextPageSuccessMessage("#alert-container");
            }
        });
    }
}

function isValid(str) {
    var errorCode = 0;
    if (isEmpty(str) || isBlank(str)) {
        errorCode = 1;
    }
    return errorCode;
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}

function formatDate(date) {
    let d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) month = '0' + month;
    if (day.length < 2) day = '0' + day;

    return [year, month, day].join('-');
}

function parseDate(dateStr) {
    // Assume the format is DD/MM/YYYY
    const parts = dateStr.split('/');
    const day = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10) - 1; // Month is zero-based in JavaScript
    const year = parseInt(parts[2], 10);
    return new Date(year, month, day);
}

function calculateCashbackStatus(cashbackpublicationstartdate, cashbackpublicationenddate, accumulationstartdate, accumulationenddate, fruitionstartdate, fruitionenddate) {
    let today = formatDate(new Date());
    
    // Parse the dates from DD/MM/YYYY format
    cashbackpublicationstartdate = formatDate(parseDate(cashbackpublicationstartdate));
    cashbackpublicationenddate = formatDate(parseDate(cashbackpublicationenddate));
    accumulationstartdate = formatDate(parseDate(accumulationstartdate));
    accumulationenddate = formatDate(parseDate(accumulationenddate));
    fruitionstartdate = formatDate(parseDate(fruitionstartdate));
    fruitionenddate = formatDate(parseDate(fruitionenddate));

    let status = "";
    if (cashbackpublicationstartdate <= today && today < accumulationstartdate) {
        status = "Pubblicazione";
    } else if (accumulationstartdate <= today && today <= accumulationenddate) {
        status = "Accumulo";
    } else if (accumulationenddate < today && today < fruitionstartdate) {
        status = "Attesa";
    } else if (fruitionstartdate <= today && today <= fruitionenddate) {
        status = "Fruizione";
    } else if (fruitionenddate < today) {
        status = "Terminato";
    } else {
        status = "";
    }
    return status;
}


function cashbackRenderTable(dataTable, data) {
    dataTable.clear();
    $(".alert").alert('close');
    if (data && data.header && data.header.result === 'OK') {
        if (data.payload && !jQuery.isEmptyObject(data.payload)){
            $.each(data.payload.cashbackList, function(ind, obj){
                var statusText = obj.status === 1 ? 'Si' : 'No';
                var statusCashback = calculateCashbackStatus(obj.cashbackpublicationstartdate, obj.cashbackpublicationenddate, obj.accumulationstartdate, obj.accumulationenddate, obj.fruitionstartdate, obj.fruitionenddate);
                dataTable.row.add([
                    getIconWithAction("goToUrl('" + contextPath + "/cashback/edit?id=" + obj.cashbackid + "')" , "fas fa-edit", "Modifica"),
                    obj.cashbackid,
                    obj.cashbacktitle,
                    obj.cashbackdescription,
                    obj.cashbackpublicationstartdate,
                    obj.cashbackpublicationenddate,
                    statusCashback,
                    statusText,
                    getIconWithAction("deleteCashback('" + contextPath + "/rest/cashback/delete?id=" + obj.cashbackid + "','" + obj.cashbackid + "')" , "fas fa-trash", "Test")
                ]);
            });
            sessionStorage.setItem("latestSearchData", JSON.stringify(data));
        } else {
            infoDialog.show('Info', 'Nessun risultato trovato', 'OK', '');
        }
    } else {
        showAlert('Errore durante la ricerca Cashback', 'danger', '#alert-container');
    }
    dataTable.draw();
}

// Funzione per eliminare il cashback
function deleteCashback(url, id) {
    $("#cashbackIdToDelete").text(id);
    $("#confirmationModal").modal('show');

    $("#confirmDeleteBtn").off("click").on("click", function() {
        $.ajax({
            type: "POST",
            url: url,
            data: {
                id: id
            },
            success: function(data) {
                if (data && data.header && data.header.result === "OK") {
                    setNextPageSuccessMessage("Cancellazione riuscita per il Cashback Id " + id);
                    $('#buttonSearch').click();
                } else {
                    unblockScreen();
                    showAlert(data && data.header && data.header.errorMessage ? data.header.errorMessage : "Eliminazione cashback fallita", "danger", "#alert-container");
                }
            },
            error: function(xhr, textStatus, errorThrown) {
                // Operazioni da eseguire in caso di errore
                unblockScreen();
                $(".alert").alert('close');
                showAlert('Eliminazione cashback fallita', 'danger', '#alert-container');
            }
        });
        $("#confirmationModal").modal('hide');
    });
}
