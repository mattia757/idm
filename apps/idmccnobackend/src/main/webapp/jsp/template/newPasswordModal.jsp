<!-- Modal -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <div class="modal fade" id="newPasswordModal" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
                
          <div class="modal-header">
            <h5 class="modal-title" id="staticBackdropLabel">Modifica la tua password</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
            <form role="form">

            <div class="modal-body">
                <div class="form-group row">
                    <div class="col">
                        <div id="result-container"></div>
                    </div>
                </div>
                
                <input type="hidden" id="username" name="username" value="${userProfile.username}" style="display:none;">
                <input type="hidden" id="token" name="token" value="<c:out value = "${token}"/>" style="display:none;">
                
                <div class="form-group row">
                    <div class="col">
                        <label for="newPassword">Password attuale</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <span toggle="#currentPassword" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </span>
                            </div>
                            <input type="password" class="form-control" id="currentPassword" name="currentPassword" placeholder="Inserisci password attuale" value="${currentPassword}" aria-describedby="validationTooltipUsernamePrepend" required>
                            
                        </div>
                    </div>  
                </div>
                <div class="form-group row">
                    <div class="col">
                        <label for="newPassword">Nuova password</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <span toggle="#newPassword" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </span>
                            </div>
                            <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="Inserisci nuova password" maxlength="20" value="${newPassword}" aria-describedby="validationTooltipUsernamePrepend" required>
                            <small class="form-text text-muted">
                                La password deve essere lunga almeno 8 caratteri e contenere almeno una lettera maiuscola, una minuscola, un numero e un carattere speciale<br>(|!"$%&/()=?)
                            </small>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col">
                        <label for="repeatNewPassword">Ripeti nuova password</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">
                                    <span toggle="#repeatNewPassword" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </span>
                            </div>
                            <input type="password" class="form-control" id="repeatNewPassword" name="repeatNewPassword" placeholder="Ripeti nuova password" maxlength="20" value="${repeatNewPassword}" aria-describedby="validationTooltipUsernamePrepend" required>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="closeNewPasswordModal" class="btn btn-secondary float-right ml-1" data-dismiss="modal">Chiudi</button>
<!--                <button type="submit" id="updatePasswordButton" class="btn btn-primary float-right ml-1">Conferma</button>-->
                <button type="button" id="updatePasswordButton" class="btn btn-primary float-right ml-1">Conferma</button>
            </div>
          </form>
        </div>
      </div>
    </div>
                    
