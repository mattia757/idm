$(".overlay").hide();
$.fn.dataTable.moment('DD/MM/YYYY');
var dataTableUtenti = $('#dataTableUtenti').DataTable({
    language: {
        url: contextPath+"/resources/vendor/datatables/italian.json"
    },
    searching: false,
    autoWidth: false,
//    order: [0, 'asc'],
    columnDefs: [
        {
            targets: 0, 
            orderable: false
        },
        {
            targets: 4, 
            className: 'text-td-center'
        }
    ],    
    fixedColumns: true,
//     chiamata searchUser all'onload della pagina
//    ajax: {
//        url: contextPath+"/rest/clienti/searchUser",
//        type: "POST",
//        success: function(data) {
//            utentiRenderTable(dataTableUtenti, data);
//        }
//    }
    });

$('#dataTableUtenti').on( 'page.dt', function () {
    var info = dataTableUtenti.page.info();
    //alert( 'Showing page: '+info.page+' of '+info.pages );
    sessionStorage.setItem("lastOpenPage",info.page);
} );

$('#dataTableUtenti').on( 'draw.dt', function () {
    //alert( 'Table redrawn' );
    if (sessionStorage.getItem("lastOpenPage")!==null) {
        var info = dataTableUtenti.page.info();
        var page = parseInt(sessionStorage.getItem("lastOpenPage"));
        if (info.page!==page) {
            sessionStorage.removeItem("lastOpenPage");
            dataTableUtenti.page(page).draw('page');
        }
    }
} );
    
if (sessionStorage.getItem("recoverLastSearch")!==null) {
    var name = sessionStorage.getItem("name");
    var surname = sessionStorage.getItem("surname");
    var email = sessionStorage.getItem("email");
    var eanCard = sessionStorage.getItem("eanCard");
    var coopId = sessionStorage.getItem("coopId");
    var latestSearchData = JSON.parse(sessionStorage.getItem("latestSearchData"));
    if (isValid(name)===0) {
        $("#name").val(name);
    }
    if (isValid(surname)===0) {
        $("#surname").val(surname);
    }
    if (isValid(email)===0) {
        $("#email").val(email);
    }
    if (isValid(eanCard)===0) {
        $("#eanCard").val(eanCard);
    }
    if (isValid(coopId)===0) {
        $("#coopId").val(coopId);
    }
    blockScreen();
    searchUtenti(latestSearchData);
}

$("#buttonSearch").click(function() {
    blockScreen();
    resetLastSearch();
    searchUtenti(null);
});

$("form#searchUsersForm :input").each(function(){
    var input = $(this);
    input.on('keypress', function(e){
        if (e.keyCode === 13){
            e.keyCode = 188;
            e.preventDefault();
            blockScreen();
            resetLastSearch();
            searchUtenti(null);
        };
    });
});

function resetLastSearch() {
    sessionStorage.removeItem("recoverLastSearch");
    sessionStorage.removeItem("lastOpenPage");
}

function searchUtenti(latestSearchData) {
    
    var name = $("#name").val().trim();
    var surname = $("#surname").val().trim();
    var email = $("#email").val().trim();
    var eanCard = $("#eanCard").val().trim();
    var coopId = $("#coopId").val();
    if (isValid(name)===0 || isValid(surname)===0 || isValid(email)===0 || isValid(eanCard)===0) {
        sessionStorage.setItem("name",name);
        sessionStorage.setItem("surname",surname);
        sessionStorage.setItem("email",email);
        sessionStorage.setItem("eanCard",eanCard);
        sessionStorage.setItem("coopId",coopId);
        sessionStorage.setItem("recoverLastSearch","1");
        if (latestSearchData!==null) {
            unblockScreen();
            utentiRenderTable(dataTableUtenti, latestSearchData, null, null);
        } else {
            $.ajax({
                type: "POST",
                url: contextPath+"/rest/clienti/searchUser",
                data: {
                    name: name,
                    surname: surname,
                    email: email,
                    eanCard: eanCard,
                    coopId: coopId
                },

                success: function(data, message, response) {
                    unblockScreen();
                    utentiRenderTable(dataTableUtenti, data, message, response);
                }
            });
        }
    } else {
        unblockScreen();
        if (isValid(name)===1 && isValid(surname)===1 && isValid(email)===1 && isValid(eanCard)===1) {
            infoDialog.show('Attenzione','Almeno uno dei filtri di ricerca deve essere compilato', 'OK', '');
        }
        if (isValid(name)===2 || isValid(surname)===2 || isValid(email)===2 || isValid(eanCard)===2) {
            infoDialog.show('Attenzione','Inserire almeno 2 caratteri nei filtri di ricerca', 'OK', '');
        }
    }
}
function isValid(str) {
    var errorCode = 0;
    if (isEmpty(str) || isBlank(str)) {
        errorCode = 1;
    } else if (str.length<2) {
        errorCode = 2;
    }
    return errorCode;
    //return (!(isEmpty(str)) && !(isBlank(str)) && str.length>=2);
}

function isEmpty(str) {
    return (!str || 0 === str.length);
}

function isBlank(str) {
    return (!str || /^\s*$/.test(str));
}

function utentiRenderTable(dataTable, data, message, response) {
    
    if (response!==null && response.responseText.includes("html")) {
        goToUrl(contextPath+"/login")
    } else {
        dataTable.clear();
        $(".alert").alert('close');
        if (data.header.result === 'OK') { 
            if (!jQuery.isEmptyObject(data.payload)){
                $.each(data.payload.users, function(ind, obj){
                    dataTable.row.add([
                        getIconWithAction("goToUrl('"+contextPath+"/clienti/view?userId="+obj.userId+"')" , "file-alt fa-lg", "Visualizza") + " ",
                        convertNullToDash(obj.name),
                        convertNullToDash(obj.surname),
                        obj.email,
                        convertTextToIcon(obj.emailVerified),
                        arrayToString(obj.eanCards),
                        coopIdToName(obj.coopId),
                        convertNullToDash(obj.birthDate)
                    ]);
                });
                sessionStorage.setItem("latestSearchData",JSON.stringify(data)); // save search result in sessionStorage
            } else {
                infoDialog.show('Info','Nessun risultato trovato', 'OK', '');
            }
        } else {
            showAlert('Errore: '+data.header.errorMessage+'', 'danger', '#alert-container');
        }
        dataTable.draw();
        }
     
}

function arrayToString(arr) {
  let str = '';
  arr.forEach(function(i, index) {
    str += i.eanCard;
    if (index != (arr.length - 1)) {
      str += ', ';
    };
  });
  return str;
}

function convertTextToIcon(emailVerified){
    let str = '';
    if (emailVerified === 'true'){
        str = '<span toggle="" class="fa fa-check field-icon" style="color: #2ed573;"></span>';
    } else {
        str = '<span toggle="" class="fa fa-times field-icon" style="color: #ff7f50;"></span>';
    }
    return str;
}

function convertNullToDash(value) {
    if (value === null || typeof value === 'undefined' || value === 'null') {
        return '-';
    }
    return value;
}

function coopIdToName(coopId) {
    
    switch (coopId) {
        
      case "1":
        coopId = "Novacoop";
        break;
      case "2":
        coopId = "Coop Liguria";
        break;
      case "3":
        coopId = "Coop Lombardia";
        break;
        case "40":
        coopId = "CVL";
        break;

    }
    return coopId;
}

function getFormParams() {
    return {
        name: $("#name").val(),
        surname: $("#surname").val(),
        email: $("#email").val(),
        eanCard: $("#eanCard").val(),
        coopId: $("#coopId").val()
    };
}

function exportUtenti() {
    
    var formParams = getFormParams();
    formParams.output = 'excel';
    postToUrl(contextPath+"/rest/clienti/searchUser", formParams);
}
