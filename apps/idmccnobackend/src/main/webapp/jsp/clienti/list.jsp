<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="alert-container"></div>
<!--<div id="spinner-container"></div>-->



<form class="form" role="form" id="searchUsersForm">
    <c:if test="${userProfile.role == 'ADMIN' || userProfile.role == 'CCNO'}">
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="coopId">Cooperativa</label>
                    <select id="coopId" class="form-control">
                        <option value="">Scegli...</option>
                        <option value="1">Novacoop</option>
                        <option value="2">Coop Liguria</option>
                        <option value="3">Coop Lombardia</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    </c:if>
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <c:if test="${userProfile.role != 'ADMIN' && userProfile.role != 'CCNO'}">
            <input type="text" style="display: none;" class="form-control" id="coopId" readonly value="${userProfile.idCooperativa}">
            </c:if>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="name">Nome:</label>
                    <input type="text" class="form-control" id="name" placeholder="Nome">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="surname">Cognome</label>
                    <input type="text" class="form-control" id="surname" placeholder="Cognome">
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="email">Email</label>
                    <input type="text" class="form-control" id="email" placeholder="Email">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="eanCard">Tessera socio:</label>
                    <input type="text" class="form-control" id="eanCard" placeholder="Tessera socio">
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group float-right">
                    <button type="reset" class="btn btn-secondary">Cancella</button>
                    <button type="button" id="buttonSearch" class="btn btn-primary">Cerca</button>
                </div>
            </div>
        </div>
    </div>
    <div>
        <table id="dataTableUtenti" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th class="col" data-field="actions"></th>
                    <th class="col" data-field="nome">Nome</th>
                    <th class="col" data-field="cognome">Cognome</th>
                    <th class="col" data-field="email">Email</th>
                    <th class="col" data-field="emailVerificata">Email verificata</th>
                    <th class="col" data-field="cartaSocio">Carte Socio</th>
                    <th class="col" data-field="cooperativa">Cooperativa</th>
                    <th class="col" data-field="dataNascita">Data di nascita</th>
                    
                </tr>
            </thead>
        </table>
    </div>
</form>
<% request.setAttribute("pageJavascript", "/clienti/list"); %>
