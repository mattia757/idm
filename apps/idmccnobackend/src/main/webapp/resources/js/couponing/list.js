$("#couponingDate").datetimepicker({
    format: 'DD/MM/YYYY',
    locale: 'it',
    useCurrent: false
});

$("#importDate").datetimepicker({
  format: 'DD/MM/YYYY',
  locale: 'it',
  useCurrent: false
});

$(document).ready(function() {
    $("#buttonSearch").click();
});

$(".overlay").hide();

$.fn.dataTable.moment('DD/MM/YYYY');

var dataTableCouponing = $('#dataTableCouponing').DataTable({
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

$('#dataTableCouponing').on('page.dt', function () {
    var info = dataTableCouponing.page.info();
    sessionStorage.setItem("lastOpenPage", info.page);
});

$('#dataTableCouponing').on('draw.dt', function () {
    if (sessionStorage.getItem("lastOpenPage") !== null) {
        var info = dataTableCouponing.page.info();
        var page = parseInt(sessionStorage.getItem("lastOpenPage"));
        if (info.page !== page) {
            sessionStorage.removeItem("lastOpenPage");
            dataTableCouponing.page(page).draw('page');
        }
    }
});

if (sessionStorage.getItem("recoverLastSearch") !== null) {
    var id = sessionStorage.getItem("id");
    var barcode = sessionStorage.getItem("barcode");
    var couponingDate = sessionStorage.getItem("couponingDate");

    if (isValid(id) === 0) {
        $("#id").val(id);
    }
    if (isValid(barcode) === 0) {
        $("#barcode").val(barcode);
    }
    if (isValid(couponingDate) === 0) {
        $("#couponingDate").val(couponingDate);
    }

    blockScreen();
    searchCouponing(JSON.parse(sessionStorage.getItem("latestSearchData")));
}

$("#buttonSearch").click(function() {
    blockScreen();
    resetLastSearch();
    searchCouponing(null);
});

$("form#searchCouponingForm :input").each(function(){
    var input = $(this);
    input.on('keypress', function(e){
        if (e.keyCode === 13){
            e.keyCode = 188;
            e.preventDefault();
            blockScreen();
            resetLastSearch();
            searchCouponing(null);
        };
    });
});

function resetLastSearch() {
    sessionStorage.removeItem("recoverLastSearch");
    sessionStorage.removeItem("lastOpenPage");
}

function searchCouponing(latestSearchData) {
    blockScreen();
    
    var id = $("#idVoucher").length ? $("#idVoucher").val().trim() : "";
    var barcode = $("#barcode").length ? $("#barcode").val().trim() : "";
    var couponingDate = $("#couponingDate").length ? $("#couponingDate").val().trim() : "";
    sessionStorage.setItem("idVoucher", id);
    sessionStorage.setItem("barcode", barcode);
    sessionStorage.setItem("couponingDate", couponingDate);
    sessionStorage.setItem("recoverLastSearch", "1");

    if (latestSearchData !== null) {
        unblockScreen();
        couponingRenderTable(dataTableCouponing, latestSearchData, null, null);
    } else {
        $.ajax({
            type: "POST",
            url: contextPath + "/rest/couponing/searchCoupon",
            data: {
                voucherId: id,
                barcode: barcode,
                voucherDate: couponingDate
            },
            success: function(data, message, response) {
                unblockScreen();
                couponingRenderTable(dataTableCouponing, data, message, response);
                showNextPageSuccessMessage("#alert-container");
            }
        });
    }
}

function isValid(str) {
    return (!str || /^\s*$/.test(str)) ? 1 : 0;
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
    const parts = dateStr.split('/');
    const day = parseInt(parts[0], 10);
    const month = parseInt(parts[1], 10) - 1;
    const year = parseInt(parts[2], 10);
    return new Date(year, month, day);
}

function couponingRenderTable(dataTable, data, message, response) {
    
    if (response !== null && response.responseText.includes("html")) {
        goToUrl(contextPath + "/login");
    } else {
        dataTable.clear();
        $(".alert").alert('close');
        if (data.header.result === 'OK') { 
            if (!jQuery.isEmptyObject(data.payload)){
                $.each(data.payload.couponingList, function(ind, obj){
                    // L'ID viene codificato prima di inserirlo nell'handler inline:
                    // alcuni voucher possono contenere caratteri che altererebbero URL o JavaScript.
                    var encodedVoucherId = encodeURIComponent(String(obj.idVoucher)).replace(/'/g, "%27");
                    dataTable.row.add([
                        getIconWithAction("goToUrl('" + contextPath + "/couponing/edit?voucherId=" + obj.idVoucher + "')" , "fas fa-edit", "Modifica"),
                        obj.idVoucher,
                        obj.idType,
                        obj.headerMSG,
                        obj.voucherType,
                        obj.dataInizio,
                        obj.dataFine,
                        obj.value,
                        getIconWithAction("deleteCoupon('" + encodedVoucherId + "')" , "fas fa-trash", "Elimina")
                    ]);
                });
                sessionStorage.setItem("latestSearchData", JSON.stringify(data));
            } else {
                infoDialog.show('Info', 'Nessun risultato trovato', 'OK', '');
            }
        } else {
            showAlert('Errore: ' + data.header.errorMessage, 'danger', '#alert-container');
        }
        dataTable.draw();
    }
}

function deleteCoupon(encodedId) {
    var id = decodeURIComponent(encodedId);
    $("#couponIdToDelete").text(id);
    $("#confirmationModal").modal('show');

    $("#confirmDeleteBtn").off("click").on("click", function() {
        $.ajax({
            type: "POST",
            url: contextPath + "/rest/couponing/delete",
            data: {
                id: id
            },
            success: function(data) {
                if (data && data.header && data.header.result === "OK") {
                    setNextPageSuccessMessage("Cancellazione riuscita per il Coupon Id " + id);
                    $('#buttonSearch').click();
                } else {
                    unblockScreen();
                    showAlert(data && data.header && data.header.errorMessage ? data.header.errorMessage : "Eliminazione coupon fallita", "danger", "#alert-container");
                }
            },
            error: function() {
                unblockScreen();
                $(".alert").alert('close');
                showAlert('Eliminazione coupon fallita', 'danger', '#alert-container');
            }
        });
        $("#confirmationModal").modal('hide');
    });
}

// Mostra la modal Import
$("#buttonImport").on("click", function () {
  // reset valore (opzionale)
  $("#importDate").val("");
  $("#importModal").modal("show");
});

// Pulsante INDIETRO nella modal -> torna alla pagina ricerca (chiude modal)
$("#importBackBtn").on("click", function () {
  $("#importModal").modal("hide");
  goToSearchWithToken(); // vedi helper sotto
});

// Pulsante OK nella modal -> chiama REST d'import con body JSON { importDate: 'dd/MM/yyyy' }
$("#importOkBtn").on("click", function () {
  var importDate = ($("#importDate").val() || "").trim();

  if (!importDate) {
    $(".alert").alert('close');
    showAlert('Seleziona una data per procedere con l\'import', 'warning', '#alert-container');
    return;
  }

  blockScreen();
  $.ajax({
    type: "POST",
    url: contextPath + "/rest/couponing/import",
    contentType: "application/json",
    data: JSON.stringify({ importDate: importDate }),
    success: function (data) {
      unblockScreen();
      $("#importModal").modal("hide");
      $(".alert").alert('close');
      showAlert('Import avviato correttamente', 'success', '#alert-container');
      // se vuoi ricaricare subito la tabella:
      $("#buttonSearch").click();
    },
    error: function () {
      unblockScreen();
      $(".alert").alert('close');
      showAlert('Errore durante l\'import', 'danger', '#alert-container');
    }
  });
});

// helper per tornare alla pagina ricerca con token
function goToSearchWithToken() {
  var url = contextPath + "/couponing/ricerca";
  window.location.href = url;
}

