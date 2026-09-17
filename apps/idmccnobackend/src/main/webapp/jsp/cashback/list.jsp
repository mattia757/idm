<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="alert-container"></div>

<form class="form" role="form" id="searchCashbackForm">
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="id">ID:</label>
                    <input type="text" class="form-control" id="id" placeholder="ID">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="titolo">Titolo:</label>
                    <input type="text" class="form-control" id="titolo" placeholder="Titolo">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="statoCashback">Stato Cashback:</label>
                    <select class="form-control" id="statoCashback">
                        <option value="" selected>Scegli stato...</option>
                        <option value="P">P - Pubblicazione</option>
                        <option value="A">A - Accumulo</option>
                        <option value="W">W - Attesa</option>
                        <option value="F">F - Fruizione</option>
                        <option value="T">T - Terminato</option>
                    </select>
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
        <table id="dataTableCashback" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th class="col" data-field="actions"></th>
                    <th class="col" data-field="id">ID</th>
                    <th class="col" data-field="titolo">Titolo</th>
                    <th class="col" data-field="descrizione">Descrizione</th>
                    <th class="col" data-field="dataInizio">Data Inizio</th>
                    <th class="col" data-field="dataFine">Data Fine</th>
                    <th class="col" data-field="attivo">Stato</th>
                    <th class="col" data-field="attivo">Attivo</th>
                    <th class="col" data-field="delete"></th>
                </tr>
            </thead>
        </table>
    </div>
    <div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="confirmationModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="confirmationModalLabel">Conferma eliminazione</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    Sei sicuro di voler eliminare questo cashback (<span id="cashbackIdToDelete"></span>)?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                    <button type="button" class="btn btn-danger" id="confirmDeleteBtn">Elimina</button>
                </div>
            </div>
        </div>
    </div>
</form>

<% request.setAttribute("pageJavascript", "/cashback/list"); %>