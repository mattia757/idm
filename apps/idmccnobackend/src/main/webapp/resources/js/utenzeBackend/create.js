
$('#createUserBeButton').click(function() {
    blockScreen();
    createUserBe(); 
});

function createUserBe() {
    
    var username = $("#username").val();
    var password  = $("#password").val();
    var repeatPassword = $("#repeatPassword").val();
    var role = $("#userRole").val();
    var coop = $("#coop").val();
    var customerCancellation = $("#customerCancellation").val();
    
    $.ajax({
        type: "POST",
        url: contextPath+"/usersBe/create",
        data: {
            username: username,
            password: password,
            repeatPassword: repeatPassword,
            role: role,
            coop: coop,
            customerCancellation: customerCancellation
        },
        success: function(data, message, response) {
            unblockScreen();
            showCreateUserBeResult(data, message, response);
        }
    });
}

function showCreateUserBeResult(data, message, response){
    if (data.header.result === 'OK') {
        $(".alert").alert('close');
        showAlert('Utente creato correttamente', 'success', '#alert-container');
    } else {
        $(".alert").alert('close');
        showAlert('Errore: ' +data.header.errorMessage+ '', 'danger', '#alert-container');
    }
}