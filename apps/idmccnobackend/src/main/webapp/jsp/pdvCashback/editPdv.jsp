<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<head>
    <!-- Altri tag head... -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.1.0-beta.1/css/select2.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.1.0-beta.1/js/select2.min.js"></script>
</head>

<div id="alert-container"></div>

<form name="form" class="form needs-validation" role="form" action="<c:url value="/cashback/savePdv" />" method="POST" novalidate enctype="multipart/form-data" modelAttribute="cashbackPdvIds" autocomplete="off">

    <!-- Nascondi il menu se sono in creazione di un nuovo punto vendita per non avere la possibilit� di spostarmi sugli altri Tab -->
    <c:if test="${not empty cashback.cashbackid}">
        <jsp:include page="../cashback/headerTab.jsp" flush="true" /> 
        <jsp:include page="../cashback/headerDetailsCashback.jsp" flush="true"></jsp:include>
    </c:if>
    
    <div class="card mb-3">  
        <div class="card-header"><strong>Novacoop</strong></div>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-12"> 
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label form-label-bg" for="allSelectedNovacoop">Tutti i Pdv</label>
                        <div class="col-sm-3">
                            <input type="checkbox" id="allSelectedNovacoop" ${novacooopAllSelected == '1' ? 'checked' : ''}>
                        </div>
                        <label class="col-sm-2 col-form-label form-label-bg" for="pdvSelectedForNovacoop">Lista PDV</label>
                        <div class="col-sm-3">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="pdvSelectedForNovacoopDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Seleziona i PDV
                                </button>
                                <div class="dropdown-menu scrollable-menu" aria-labelledby="pdvSelectedForNovacoopDropdown">
                                    <form>
                                        <c:forEach var="entry" items="${novacooopPdvIdSelected}">
                                            <div class="form-check">
                                                <input type="checkbox" class="form-check-input" id="${fn:replace(entry.id, ' ', '_')}" name="pdvSelectedForNovacoop" value="${entry.value}" ${entry.selected == '1' ? 'checked' : ''}>
                                                <label class="form-check-label" for="${fn:replace(entry.id, ' ', '_')}">${entry.value}</label>
                                            </div>
                                        </c:forEach>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="listPdvIdForNovacoop" style="display: none;"></div>
    </div> 
                
    <div class="card mb-3">  
        <div class="card-header"><strong>Coop Liguria</strong></div>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-12"> 
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label form-label-bg" for="allSelectedLiguria">Tutti i Pdv</label>
                        <div class="col-sm-3">
                            <input type="checkbox" id="allSelectedLiguria" ${liguriaAllSelected == '1' ? 'checked' : ''}>
                        </div>
                        <label class="col-sm-2 col-form-label form-label-bg" for="pdvSelectedForLiguria">Lista PDV</label>
                        <div class="col-sm-3">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="pdvSelectedForLiguriaDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Seleziona i PDV
                                </button>
                                <div class="dropdown-menu scrollable-menu" aria-labelledby="pdvSelectedForLiguriaDropdown">
                                    <form>
                                        <c:forEach var="entry" items="${liguriaPdvIdSelected}">
                                            <div class="form-check">
                                                <input type="checkbox" class="form-check-input" id="${fn:replace(entry.id, ' ', '_')}" name="pdvSelectedForLiguria" value="${entry.value}" ${entry.selected == '1' ? 'checked' : ''}>
                                                <label class="form-check-label" for="${fn:replace(entry.id, ' ', '_')}">${entry.value}</label>
                                            </div>
                                        </c:forEach>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="listPdvIdForLiguria" style="display: none;"></div>
    </div>
                    
    <div class="card mb-3">  
        <div class="card-header"><strong>Coop Lombardia</strong></div>
        <div class="card-body">
            <div class="row">
                <div class="col-sm-12"> <!-- Usa una colonna pi� ampia -->
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label form-label-bg" for="allSelectedLombardia">Tutti i Pdv</label>
                        <div class="col-sm-3">
                            <input type="checkbox" id="allSelectedLombardia" ${lombardiaAllSelected == '1' ? 'checked' : ''}>
                        </div>
                        <label class="col-sm-2 col-form-label form-label-bg" for="pdvSelectedForLombardia">Lista PDV</label>
                        <div class="col-sm-3">
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" id="pdvSelectedForLombardiaDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Seleziona i PDV
                                </button>
                                <div class="dropdown-menu scrollable-menu" aria-labelledby="pdvSelectedForLombardiaDropdown">
                                    <form>
                                        <c:forEach var="entry" items="${lombardiaPdvIdSelected}">
                                            <div class="form-check">
                                                <input type="checkbox" class="form-check-input" id="${fn:replace(entry.id, ' ', '_')}" name="pdvSelectedForLombardia" value="${entry.value}" ${entry.selected == '1' ? 'checked' : ''}>
                                                <label class="form-check-label" for="${fn:replace(entry.id, ' ', '_')}">${entry.value}</label>
                                            </div>
                                        </c:forEach>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="listPdvIdForLombardia" style="display: none;"></div>
    </div>
 
    <input type="hidden" id="cashbackId" name="cashbackId" value="${cashback.cashbackid}">
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" id="saveCashbackPdvIds" class="btn btn-primary float-right ml-1">Salva</button>   
            <button type="button" id="returnPdv" class="btn btn-secondary float-right ml-1" onclick="goToUrl()">Indietro</button>
        </div>
    </div> 
                    
</form>

<style>
    .scrollable-menu {
        height: auto;
        max-height: 200px; /* Imposta l'altezza massima della dropdown */
        overflow-x: hidden;
    }
</style>
    
    
<% request.setAttribute("pageJavascript", "/pdvCashback/editPdv"); %>
