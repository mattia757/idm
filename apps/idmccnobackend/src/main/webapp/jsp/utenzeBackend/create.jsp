<%-- 
    Document   : list
    Created on : Aug 24, 2020, 12:12:51 PM
    Author     : DelorenziVa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="alert-container"></div>
<!--<div id="spinner-container"></div>-->
<form class="form" role="form" id="createBeUserForm">
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="username">Username</label>
                    <input type="text" class="form-control" id="username" maxlength="20" placeholder="Username" required>
                    <small class="form-text text-muted">
                    Massimo 20 caratteri
                    </small>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <label for="password">Password</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <span toggle="#password" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                        </span>
                    </div>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Inserisci password" maxlength="20" aria-describedby="validationTooltipUsernamePrepend" required>
                    
                </div>
            </div>
            <div class="col">
                <label for="repeatPassword">Ripeti password</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">
                            <span toggle="#repeatPassword" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                        </span>
                    </div>
                    <input type="password" class="form-control" id="repeatPassword" name="repeatPassword" placeholder="Ripeti password" maxlength="20" aria-describedby="validationTooltipUsernamePrepend" required>
                </div>
            </div>
        </div>   
    </div>
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <small class="form-text text-muted">
                    La password deve essere lunga almeno 8 caratteri e contenere almeno una lettera maiuscola, una minuscola, un numero e un carattere speciale (|!"$%&/()=?)
                </small>
            </div>
        </div>
    </div>
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="coop">Cooperativa</label>
                    <select id="coop" class="form-control" required>
                        <option value="">Scegli...</option>
                        <option value="1">Novacoop</option>
                        <option value="2">Coop Liguria</option>
                        <option value="3">Coop Lombardia</option>
                    </select>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="userRole">Ruolo</label>
                    <select id="userRole" class="form-control" required>
                        <option value="">Scegli...</option>
                        <option value="CCNO">CCNO</option>
                        <option value="MODIFY">Editore</option>
                        <option value="VIEW">Lettore</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-3 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="customerCancellation">Abilitato alla cancellazione clienti</label>
                    <select id="customerCancellation" class="form-control" required>
                        <option value="0">NO</option>
                        <option value="1">SI</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 offset-lg-3 justify-content-center mt-4">
        <div class="row">
            <div class="col">
                <div class="form-group float-right">
                    <button type="reset" class="btn btn-secondary">Cancella</button>
                    <button type="button" id="createUserBeButton" class="btn btn-primary">Crea</button>
                </div>
            </div>
        </div>
    </div>
</form>
<% request.setAttribute("pageJavascript", "/utenzeBackend/create"); %>
