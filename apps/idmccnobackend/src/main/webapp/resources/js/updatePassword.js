$(".toggle-password").click(function() {

    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") === "password") {
      input.attr("type", "text");
    } else {
      input.attr("type", "password");
    }
});

$('#updatePasswordButton').click(function() {
   updatePassword(); 
});

function updatePassword() {
    var token = $("#token").val();
    var username = $("#username").val();
    var currentPassword = $("#currentPassword").val();
    var newPassword = $("#newPassword").val();
    var repeatNewPassword = $("#repeatNewPassword").val();
    $.ajax({
        type: "POST",
        url: contextPath+"/usersBe/updatePassword",
        data: {
            username: username,
            currentPassword: currentPassword,
            newPassword: newPassword,
            repeatNewPassword: repeatNewPassword
        },
        success: function(data, message, response) {
            showUpdatePasswordResult(data, message, response);
        }
    });
}

function showUpdatePasswordResult(data, message, response){
    if (response.responseText.includes("html")) {
        goToUrl(contextPath+"/login")
    } else {
        if (data.header.result === 'OK') {
            login();
        } else {
            $(".alert").alert('close');
            showAlert('Errore: ' +data.header.errorMessage+ '', 'danger', '#result-container');
        }
    }
}

function login() {
    $.ajax({
        type: "POST",
        url: contextPath+"/login/auth",
        data: {
            username: $("#username").val(),
            password: $("#newPassword").val()
        },
        success: function(){
            infoDialog.show('Info','Password aggiornata correttamente', 'OK', 'closeModal()');
        }
    });
}

function closeModal() {
    $('#newPasswordModal').modal('hide');
}

$('#closeNewPasswordModal').click(function() {
    refreshPage();
});

