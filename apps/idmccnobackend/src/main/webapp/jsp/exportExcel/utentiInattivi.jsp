<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="alert-container"></div>

<form class="form" role="form" id="exportUsersForm">

    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="application">Applicativo:</label>
                    <select id="application" class="form-control" required>
                        <option value="">Scegli...</option>
                        <option value="appcoop">App Coop</option>
                        <option value="ecommerce">Ecommerce</option>
                        <option value="portal">Portale coop.it</option>
                        <option value="community">Community</option>
                        <option value="portal-nova">Portale Novacoop</option>
                        <option value="all" selected>Tutti</option>
                    </select>
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
                <div class="modal-footer"><button class="btn btn-secondary" type="button" data-dismiss="modal">Annulla</button><button class="btn btn-primary" type="button" onclick="exportUtentiInattivi()" data-dismiss="modal">Conferma</button></div>
            </div>
        </div>
    </div>
</form>
<% request.setAttribute("pageJavascript", "/exportExcel/utentiInattivi"); %>
