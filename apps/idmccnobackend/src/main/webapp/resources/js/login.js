
$("#buttonLogin").click(function() {
    login();
});

function login() {
    $.ajax({
        type: "POST",
        url: contextPath+"/rest/login/loginProcess",
        data: {
            username: $("#username").val(),
            password: $("#password").val()
        },
        success: function(data) {
            getWelcomePage(data);
        }
    });
}

function getWelcomePage(data) {
    if (data.common.ack === 'OK') {
        goToUrl(contextPath+"/")
//        $.ajax({
//        type: "GET",
//        url: contextPath+"/"
    } else {
        showAlert("Errore: "+ data.common.faultString, 'danger', '#alert-container');
    }
}
