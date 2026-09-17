function caricamentoListaNotifichePush() {
    showAlert("Caricamento lista utenti per notifiche push avviato si prega di attendere... ", 'info', '#alert-container');
    blockScreen();
    
    $('form[name="form-caricamento"]').attr("action", "/IdmCCNOBackend/caricamento/caricaNotifichePush");
    $('form[name="form-caricamento"]').submit();
}
