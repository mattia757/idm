<%-- 
    Document   : list
    Created on : Aug 24, 2020, 12:12:51 PM
    Author     : DelorenziVa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="alert-container"></div>
<!--<div id="spinner-container"></div>-->
<form class="form" role="form" id="searchBeUsersForm">
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="username">Username</label>
                    <input type="text" class="form-control" id="username" placeholder="Username">
                </div>
            </div>
            
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="userRole">Ruolo</label>
                    <select id="userRole" class="form-control">
                        <option value="">Scegli...</option>
                        <option value="CCNO">CNNO</option>
                        <option value="MODIFY">Editore</option>
                        <option value="VIEW">Lettore</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6 offset-lg-3 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="coop">Cooperativa</label>
                    <select id="coop" class="form-control">
                        <option value="">Scegli...</option>
                        <option value="0">CCNO</option>
                        <option value="1">Novacoop</option>
                        <option value="2">Coop Liguria</option>
                        <option value="3">Coop Lombardia</option>
                    </select>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="customerCancellation">Abilitato alla cancellazione clienti</label>
                    <select id="customerCancellation" class="form-control">
                        <option value="">Scegli...</option>
                        <option value="0">NO</option>
                        <option value="1">SI</option>
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
        <table id="dataTableUtenzeBackend" class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th class="col" data-field="actions"></th>
                    <th class="col" data-field="username">Username</th>
                    <th class="col" data-field="role">Ruolo</th>
                    <th class="col" data-field="coop">Cooperativa</th>
                    <th class="col" data-field="customerCancellation">Cancellazione clienti</th>
                </tr>
            </thead>
        </table>
    </div>
</form>
<% request.setAttribute("pageJavascript", "/utenzeBackend/list"); %>
