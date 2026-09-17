<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="alert-container"></div>

<form class="form" role="form" id="searchCouponingForm">
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="idVoucher">ID Voucher:</label>
                    <input type="text" class="form-control" id="idVoucher" placeholder="ID">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="barcode">Barcode:</label>
                    <input type="text" class="form-control" id="barcode" placeholder="Barcode">
                </div>
            </div>
            <div class="col">
<!--                <div class="form-group">
                    <label class="control-label" for="dataVoucher">Data Voucher:</label>
                    <div class="input-group date" id="datepicker">
                        <input type="text" class="form-control" id="dataVoucher" placeholder="gg-mm-aaaa" autocomplete="off"/>
                        <div class="input-group-append">
                            <span class="input-group-text">
                                <i class="fa fa-calendar"></i>
                            </span>
                        </div>
                    </div>
                </div>-->
                <label class="control-label" for="couponingDate">Data Coupon:</label>
                    <div class="form-group">
                        <input type="text" class="form-control date" id="couponingDate" name="couponingDate" pattern="dd/MM/yyyy" placeholder="gg/mm/aaaa"/>
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
        <table id="dataTableCouponing" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th class="col" data-field="actions"></th>
                    <th class="col" data-field="id">ID</th>
                    <th class="col" data-field="idType">Id Type</th>
                    <th class="col" data-field="headerMsg">Header MSG</th>
                    <th class="col" data-field="voucherType">Tipologia Voucher</th>
                    <th class="col" data-field="dataInizio">Data Inizio</th>
                    <th class="col" data-field="dataFine">Data Fine</th>
                    <th class="col" data-field="value">Valore</th>
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
                    Sei sicuro di voler eliminare questo coupon (<span id="couponIdToDelete"></span>)?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                    <button type="button" class="btn btn-danger" id="confirmDeleteBtn">Elimina</button>                    
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="importModal" tabindex="-1" role="dialog" aria-labelledby="importModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <h5 class="modal-title" id="importModalLabel">Importa Voucher da Data</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Chiudi">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <label class="control-label" for="importDate">Data:</label>
                    <div class="input-group">
                        <input type="text" class="form-control date" id="importDate" name="importDate" placeholder="gg/mm/aaaa" autocomplete="off"/>
                        <div class="input-group-append">
                            <span class="input-group-text"><i class="fa fa-calendar"></i></span>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" id="importBackBtn" class="btn btn-secondary">Indietro</button>
                    <button type="button" id="importOkBtn" class="btn btn-primary">OK</button>                    
                </div>

            </div>
        </div>
    </div>

</form>

<% request.setAttribute("pageJavascript", "/couponing/list"); %>


