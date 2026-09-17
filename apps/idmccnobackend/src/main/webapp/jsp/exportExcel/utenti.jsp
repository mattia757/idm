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
                                <!-- <option value="all">Tutte</option> -->
                            </select>
                        </c:when>
                        <c:otherwise>
                            <input type="text" readonly class="form-control" id="coopId" value="${userProfile.nomeCooperativa}">
                        </c:otherwise>
                    </c:choose>
                    
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="socio">Socio:</label>
                    <select id="socio" class="form-control" required>
                        <option value="">Scegli...</option>
                        <option value="1">Socio</option>
                        <option value="0">Non socio</option>
                        <option value="all" selected>Tutti</option>
                    </select>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="emailVerified">Email verificata:</label>
                    <select id="emailVerified" class="form-control" required>
                        <option value="">Scegli...</option>
                        <option value="1">Verificata</option>
                        <option value="0">Non verificata</option>
                        <option value="all" selected>Tutti</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="registrationDateFrom">Data registrazione (a partire da):</label>
                    <input type="text" class="form-control date" id="registrationDateFrom" name="registrationDateFrom" placeholder="gg/mm/aaaa">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="registrationDateTo">Data registrazione (fino a):</label>
                    <input type="text" class="form-control date" id="registrationDateTo" name="registrationDateTo" placeholder="gg/mm/aaaa">
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastModifyDateFrom">Data ultima modifica (a partire da):</label>
                    <input type="text" class="form-control date" id="lastModifyDateFrom" name="lastModifyDateFrom" placeholder="gg/mm/aaaa">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastModifyDateTo">Data ultima modifica (fino a):</label>
                    <input type="text" class="form-control date" id="lastModifyDateTo" name="lastModifyDateTo" placeholder="gg/mm/aaaa">
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="pdvId">Id Store Locator:</label>
                    <input type="text" class="form-control" id="pdvId" name="pdvId">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="includeRemovedUsers">Includi utenti cancellati:</label>
                    <select id="includeRemovedUsers" class="form-control">
                        <option value="false" selected>NO</option>
                        <option value="true">SI</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastAppAccessDateFrom">Data ultimo accesso App (a partire da):</label>
                    <input type="text" class="form-control date" id="lastAppAccessDateFrom" name="lastAppAccessDateFrom" placeholder="gg/mm/aaaa">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastAppAccessDateTo">Data ultimo accesso App (fino a):</label>
                    <input type="text" class="form-control date" id="lastAppAccessDateTo" name="lastAppAccessDateTo" placeholder="gg/mm/aaaa">
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastEcommerceAccessDateFrom">Data ultimo accesso Ecommerce (a partire da):</label>
                    <input type="text" class="form-control date" id="lastEcommerceAccessDateFrom" name="lastEcommerceAccessDateFrom" placeholder="gg/mm/aaaa">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastEcommerceAccessDateTo">Data ultimo accesso Ecommerce (fino a):</label>
                    <input type="text" class="form-control date" id="lastEcommerceAccessDateTo" name="lastEcommerceAccessDateTo" placeholder="gg/mm/aaaa">
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastCommunityAccessDateFrom">Data ultimo accesso Community (a partire da):</label>
                    <input type="text" class="form-control date" id="lastCommunityAccessDateFrom" name="lastCommunityAccessDateFrom" placeholder="gg/mm/aaaa">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastCommunityAccessDateTo">Data ultimo accesso Community (fino a):</label>
                    <input type="text" class="form-control date" id="lastCommunityAccessDateTo" name="lastCommunityAccessDateTo" placeholder="gg/mm/aaaa">
                </div>
            </div>
        </div>
    </div>
    
    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastPortalAccessDateFrom">Data ultimo accesso Portale (a partire da):</label>
                    <input type="text" class="form-control date" id="lastPortalAccessDateFrom" name="lastPortalAccessDateFrom" placeholder="gg/mm/aaaa">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastPortalAccessDateTo">Data ultimo accesso Portale (fino a):</label>
                    <input type="text" class="form-control date" id="lastPortalAccessDateTo" name="lastPortalAccessDateTo" placeholder="gg/mm/aaaa">
                </div>
            </div>
        </div>
    </div>

    <div class="col-lg-10 offset-lg-1 justify-content-center">
        <div class="row">
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastPortalNovaAccessDateFrom">Data ultimo accesso Portale Nova (a partire da):</label>
                    <input type="text" class="form-control date" id="lastPortalNovaAccessDateFrom" name="lastPortalNovaAccessDateFrom" placeholder="gg/mm/aaaa">
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <label class="control-label" for="lastPortalNovaAccessDateTo">Data ultimo accesso Portale Nova (fino a):</label>
                    <input type="text" class="form-control date" id="lastPortalNovaAccessDateTo" name="lastPortalNovaAccessDateTo" placeholder="gg/mm/aaaa">
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
                <div class="modal-footer"><button class="btn btn-secondary" type="button" data-dismiss="modal">Annulla</button><button class="btn btn-primary" type="button" onclick="exportUtenti()" data-dismiss="modal">Conferma</button></div>
            </div>
        </div>
    </div>
</form>
<% request.setAttribute("pageJavascript", "/exportExcel/utenti"); %>
