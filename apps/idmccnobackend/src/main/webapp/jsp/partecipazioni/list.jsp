<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="alert-container"></div>

<form class="form" role="form" id="searchPartecipazioniForm">
    <div class="col-lg-8 offset-lg-2 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="coopId">Cooperativa</label>
                    <c:choose>
                        <c:when test="${userProfile.idCooperativa == 0}">
                            <select id="coopId" class="form-control">
                                <option value="">Scegli...</option>
                                <option value="1">Novacoop</option>
                                <option value="2">Liguria</option>
                                <option value="3">Lombardia</option>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select id="coopIdView" class="form-control" disabled>
                                <option value="1" <c:if test="${userProfile.idCooperativa == 1}">selected</c:if>>Novacoop</option>
                                <option value="2" <c:if test="${userProfile.idCooperativa == 2}">selected</c:if>>Liguria</option>
                                <option value="3" <c:if test="${userProfile.idCooperativa == 3}">selected</c:if>>Lombardia</option>
                            </select>
                            <input type="hidden" id="coopId" value="${userProfile.idCooperativa}">
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="nome">Nome</label>
                    <input type="text" class="form-control" id="nome" placeholder="Nome">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="cognome">Cognome</label>
                    <input type="text" class="form-control" id="cognome" placeholder="Cognome">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="email">E-mail</label>
                    <input type="text" class="form-control" id="email" placeholder="E-mail">
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-8 offset-lg-2 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="dataEventoFrom">Data evento da</label>
                    <input type="text" class="form-control date" id="dataEventoFrom" placeholder="gg/mm/aaaa" autocomplete="off">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="dataEventoTo">Data evento a</label>
                    <input type="text" class="form-control date" id="dataEventoTo" placeholder="gg/mm/aaaa" autocomplete="off">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="tipologiaEvento">Tipologia Evento</label>
                    <select id="tipologiaEvento" class="form-control">
                        <option value="">Scegli...</option>
                        <c:forEach var="tipologia" items="${tipologieEventoList}">
                            <option value="${tipologia.idInteresse}">${tipologia.description}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-8 offset-lg-2 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group float-right">
                    <button type="reset" class="btn btn-secondary">Cancella</button>
                    <button type="button" id="buttonSearch" class="btn btn-primary">Cerca</button>
                    <button type="button" id="buttonExport" class="btn btn-success">Esporta xls</button>
                </div>
            </div>
        </div>
    </div>
    <div>
        <table id="dataTablePartecipazioni" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th class="col" data-field="id">ID</th>
                    <th class="col" data-field="userId">User ID</th>
                    <th class="col" data-field="email">E-mail</th>
                    <th class="col" data-field="coopId">Cooperativa</th>
                    <th class="col" data-field="tesseraSocio">Tessera socio</th>
                    <th class="col" data-field="dataEvento">Data evento</th>
                    <th class="col" data-field="luogoEvento">Luogo evento</th>
                    <th class="col" data-field="tipologiaEvento">Tipologia evento</th>
                    <th class="col" data-field="nome">Nome</th>
                    <th class="col" data-field="cognome">Cognome</th>
                    <th class="col" data-field="dataInserimento">Data inserimento</th>
                    <th class="col" data-field="actions">Azioni</th>
                </tr>
            </thead>
        </table>
    </div>
</form>

<% request.setAttribute("pageJavascript", "/partecipazioni/list"); %>
