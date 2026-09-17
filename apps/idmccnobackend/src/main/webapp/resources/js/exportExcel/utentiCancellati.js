function getFormParams() {
    return {
        coopId: $("#coopId").val(),
        email: $("#email").val()
    };
}
function exportUtentiCancellati() {
    blockScreen();
    
    var formParams = getFormParams();
    submitExportExcel(contextPath+"/exportExcel/exportUtentiCancellati", formParams, contextPath+"/exportExcel/utentiCancellati");
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
