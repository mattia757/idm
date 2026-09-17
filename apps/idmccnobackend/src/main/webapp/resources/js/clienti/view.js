// ------- verifyLoyaltyInfo ------- //

function verifyLoyaltyInfo(eanCard, birthDate, coopId) {
    
    blockScreen();
    $.ajax({
        type: "POST",
        url: contextPath+"/rest/loyalty/verifyLoyaltyInfo",
        data:{
            eanCard: eanCard, 
            birthDate: birthDate, 
            coopId: coopId
        },
        success: function(data, message, response) {
            unblockScreen();
            showResultVerifyLoyaltyInfo(data, message, response);
        }
    });
}

function showResultVerifyLoyaltyInfo(data, message, response) {
    if (response.responseText.includes("html")) {
        goToUrl(contextPath+"/login")
    } else {
        var statoCarta = data.payload.statoCarta;

        if (statoCarta == 'A') {
            statoCarta = 'Attiva';
        } else {
            statoCarta = 'Bloccata'
        }
        var registrato = data.payload.registrato;
        if (registrato == 'S') {
            registrato = 'Sì';
        } else {
            registrato = 'No'
        }
        if (data.header.result === 'OK') {
            infoDialog.show('Dati tessera','Nome: '+ data.payload.nome + '<br>Cognome: '+ data.payload.cognome + '<br>Carta valida: ' + data.payload.cartaValida + '<br>Stato carta: ' + statoCarta + '<br>Registrato: ' + registrato, 'OK', '');
        } else {
            showAlertWithoutScroll("Errore: "+data.header.errorMessage, 'danger', '#alert-container');
        }
    }
}

// ----------- removeCard ---------- //

function removeCard(userId, eanCard, birthDate, coopId) {
    
    blockScreen();
    $.ajax({
        type: "POST",
        url: contextPath+"/rest/loyalty/removeCard",
        data:{
            userId : userId,
            eanCard: eanCard,
            birthDate: birthDate,
            coopId: coopId
        },
        success: function(data, message, response) {
            unblockScreen();
            showResultRemoveCard(data, message, response);
        }
    });
}

function showResultRemoveCard(data, message, response) {
    if (response.responseText.includes("html")) {
        goToUrl(contextPath+"/login")
    } else {
        if (data.header.result === 'OK') {   
            infoDialog.show('Info','Tessera rimossa correttamente', 'OK', 'refreshPage()');
        } else {
            infoDialog.show('Errore','Errore: '+data.header.errorMessage+'', 'OK', 'refreshPage()');
        }
    }
}

$("#addCardButton").click(function() {
    blockScreen();
    addCard();
});

// ------------ addCard ------------ //

function addCard() {
    
    $.ajax({
        type: "POST",
        url: contextPath+"/rest/loyalty/addCard",
        data: {
            userId: $("#userId").val(),
            eanCard: $("#eanCard").val(),
            birthDate: $("#birthDate").val(),
            coopId: $("#coopId").val(),
        },
        success: function(data, message, response) {
            unblockScreen();
            showResultAddCard(data, message, response);
        }
    });
}

function showResultAddCard(data, message, response) {
    if (response.responseText.includes("html")) {
        $('#staticBackdrop').modal('hide');
        goToUrl(contextPath+"/login")
    } else {
        if (data.header.result === 'OK') {
            infoDialog.show('Ok','Tessera socio aggiunta correttamente', 'OK', 'refreshPage()');
            $('#staticBackdrop').modal('hide');

        } else {
            infoDialog.show('Errore','Errore: '+data.header.errorMessage+'', 'OK', 'refreshPage()');
            $('#staticBackdrop').modal('hide');
        }
    }
}

// ----- sendResetPasswordCode ----- //

function sendResetPasswordCode(email, coopId) {
    
    blockScreen();
    $.ajax({
        type: "POST",
        url: contextPath+"/rest/utils/sendResetPasswordCode",
        data:{
            email: email,
            coopId: coopId
        },
        success: function(data, message, response) {
            unblockScreen();
            showResultSendResetPasswordCode(data, message, response);
        }
    });
}

function showResultSendResetPasswordCode(data, message, response) {
    if (response.responseText.includes("html")) {
        goToUrl(contextPath+"/login")
    } else {
        if (data.header.result === 'OK') {
            infoDialog.show('Info','Codice reset password inviato correttamente', 'OK', '');
        } else {
            infoDialog.show('Errore','Errore: '+data.header.errorMessage+'', 'OK', '');
        }
    }
}

// ----- sendEmailAddressVerificationCode ----- //

function sendEmailAddressVerificationCode(email, coopId) {
    blockScreen();
    
    $.ajax({
        type: "POST",
        url: contextPath+"/rest/utils/sendEmailAddressVerificationCode",
        data:{
            email: email,
            coopId: coopId
        },
        success: function(data, message, response) {
            unblockScreen();
            showSendEmailAddressVerificationCode(data, message, response);
        }
    });
}

function showSendEmailAddressVerificationCode(data, message, response) {
    if (response.responseText.includes("html")) {
        goToUrl(contextPath+"/login")
    } else {
        if (data.header.result === 'OK') {
            infoDialog.show('Info','Codice verifica email inviato correttamente', 'OK', '');
        } else {
            infoDialog.show('Errore','Errore: '+data.header.errorMessage+'', 'OK', '');
        }
    }
}

function deleteUser(userId,email,name,surname) {
    blockScreen();
    ajaxCall(contextPath+"/rest/clienti/removeUser",
        { 
            userId: userId,
            email: email,
            name: name,
            surname: surname
        },
        function() {
            unblockScreen();
            sessionStorage.setItem("latestSearchData",null);
            infoDialog.show('Info','Utenza eliminata con successo.', 'OK', 'goToUrl(\''+contextPath+'/clienti/\')');
        }
    );
}

$("#alert-container").fadeTo(10000, 500).slideUp(500, function(){
    $("#alert-container").slideUp(500);
});
