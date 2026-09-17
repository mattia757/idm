$("#registrationDateFrom,#registrationDateTo,#lastAppAccessDateFrom,#lastAppAccessDateTo,#lastEcommerceAccessDateFrom,#lastEcommerceAccessDateTo,#lastCommunityAccessDateFrom,#lastCommunityAccessDateTo,#lastPortalAccessDateFrom,#lastPortalAccessDateTo,#lastModifyDateFrom,#lastModifyDateTo,#lastPortalNovaAccessDateFrom,#lastPortalNovaAccessDateTo").datetimepicker({
    format: 'DD/MM/YYYY',
    locale: 'it',
    useCurrent: false
});


function getFormParams() {
    return {
        coopId: $("#coopId").val(),
        socio: $("#socio").val(),
        emailVerified: $("#emailVerified").val(),
        registrationDateFrom: $("#registrationDateFrom").val(),
        registrationDateTo: $("#registrationDateTo").val(),
        lastAppAccessDateFrom: $("#lastAppAccessDateFrom").val(),
        lastAppAccessDateTo: $("#lastAppAccessDateTo").val(),
        lastEcommerceAccessDateFrom: $("#lastEcommerceAccessDateFrom").val(),
        lastEcommerceAccessDateTo: $("#lastEcommerceAccessDateTo").val(),
        lastCommunityAccessDateFrom: $("#lastCommunityAccessDateFrom").val(),
        lastCommunityAccessDateTo: $("#lastCommunityAccessDateTo").val(),
        lastPortalAccessDateFrom: $("#lastPortalAccessDateFrom").val(),
        lastPortalAccessDateTo: $("#lastPortalAccessDateTo").val(),
        lastPortalNovaAccessDateFrom: $("#lastPortalNovaAccessDateFrom").val(),
        lastPortalNovaAccessDateTo: $("#lastPortalNovaAccessDateTo").val(),
        lastModifyDateFrom: $("#lastModifyDateFrom").val(),
        lastModifyDateTo: $("#lastModifyDateTo").val(),
        pdvId: $("#pdvId").val(),
        includeRemovedUsers: $("#includeRemovedUsers").val()
    };
}
function exportUtenti() {
    blockScreen();
    
    var formParams = getFormParams();
    submitExportExcel(contextPath+"/exportExcel/exportUtenti", formParams, contextPath+"/exportExcel/utenti");
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
    }, 5000); //Check export completed (ok or ko) every 5sec, stops anyway in 60 attempts (5 minutes).
}

// remove danger alert after 1 sec
window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
}, 6000);
