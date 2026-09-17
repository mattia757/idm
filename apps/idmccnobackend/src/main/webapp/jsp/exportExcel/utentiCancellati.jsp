<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="alert-container"></div>

<form class="form" role="form" id="exportUsersForm">

    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="coopId">Cooperativa:</label>
                    <c:choose>
                        <c:when test="${userProfile.role == 'ADMIN' || userProfile.role == 'CCNO'}">
                            <select id="coopId" class="form-control" required>
                                <option value="">Scegli...</option>
                                <option value="1">Novacoop</option>
                                <option value="2">Coop Liguria</option>
                                <option value="3">Coop Lombardia</option>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <input type="text" readonly class="form-control" id="coopId" value="${userProfile.nomeCooperativa}">
                        </c:otherwise>
                    </c:choose>
                    
                </div>
                <div class="form-group">
                    <label class="control-label" for="email">Email:</label>
                    <input type="text" class="form-control" id="email" name="email">
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <!-- empty -->
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group float-right">
                    <button type="button" id="exportButton" class="btn btn-warning" data-toggle="modal" data-target="#exportModalPopup">Esporta in excel</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="exportModalPopup" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Conferma esportazione</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                </div>
                <div class="modal-body">L'esportazione potrebbe durare diversi minuti, nell'attesa si prega di non chiudere la schermata. Cliccare 'Conferma' per procedere.</div>
                <div class="modal-footer"><button class="btn btn-secondary" type="button" data-dismiss="modal">Annulla</button><button class="btn btn-primary" type="button" onclick="exportUtentiCancellati()" data-dismiss="modal">Conferma</button></div>
            </div>
        </div>
    </div>
</form>
<% request.setAttribute("pageJavascript", "/exportExcel/utentiCancellati"); %>
