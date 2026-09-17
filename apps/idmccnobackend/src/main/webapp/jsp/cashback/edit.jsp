<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="alert-container"></div>

<form name="form" class="form needs-validation" role="form" novalidate>
    <c:if test="${not empty cashback.cashbackid}">
        <jsp:include page="../cashback/headerTab.jsp" flush="true" /> 
    </c:if>
    
    <div class="card mb-3">  
        <div class="card-header"><strong>Anagrafica Cashback</strong></div>
        <div class="card-body">
            <div class="col-sm">              
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="id">Cashback Id</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control-plaintext" id="id" value="${cashback.cashbackid}" placeholder="Generato dal sistema" readonly>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="statoCashback">Stato Cashback</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control-plaintext" id="statoCashback" value="${statoCashback}" readonly>
                    </div>
                </div>     

                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackTitle">Titolo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="cashbackTitleHidden" name="cashbackTitleHidden" value="${cashback.cashbacktitle}" hidden>
                        <input type="text" class="form-control" id="cashbackTitle" name="cashbackTitle" value="" required>
                    </div>
                </div>
                
                <div class="form-group row">    
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackDescription">Descrizione</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="cashbackDescriptionHidden" name="cashbackDescriptionHidden" value="${cashback.cashbackdescription}" hidden>
                        <!--<input type="text" class="form-control" id="cashbackDescription" name="cashbackDescription" value="" required>-->
                        <textarea class="form-control" id="cashbackDescription" name="cashbackDescription" rows="5" required></textarea>
                    </div>
                </div>
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackValue">Valore</label>
                    <div class="col-sm-3">
                        <input type="number" class="form-control" id="cashbackValueHidden" name="cashbackValueHidden" max="999999999" value="${cashback.cashbackvalue}" hidden >
                        <input type="number" class="form-control" id="cashbackValue" name="cashbackValue" max="999999999" value="" required >
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="status">Attivo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="statusHidden" name="statusHidden" value="${cashback.status}" hidden>
                        <select class="form-control" id="status" name="status" required>
                            <option value="1.0" ${cashback.status == 1.0 ? 'selected' : ''}>Si</option>
                            <option value="0" ${cashback.status == 0 ? 'selected' : ''}>No</option>
                        </select>
                    </div>
                </div>
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackPublicationStartDate">Data Inizio Pubblicazione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="cashbackPublicationStartDateHidden" name="cashbackPublicationStartDateHidden" value="${cashback.cashbackpublicationstartdate}" placeholder="dd-mm-yyyy" hidden>
                        <input type="text" class="form-control" id="cashbackPublicationStartDate" name="cashbackPublicationStartDate" value="" placeholder="dd/mm/yyyy" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackPublicationEndDate">Data Fine Pubblicazione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="cashbackPublicationEndDateHidden" name="cashbackPublicationEndDateHidden" value="${cashback.cashbackpublicationenddate}" placeholder="dd-mm-yyyy" hidden>
                        <input type="text" class="form-control" id="cashbackPublicationEndDate" name="cashbackPublicationEndDate" value="" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>
                    
                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="conditionsDescription">Descrizione Condizioni</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="conditionsDescriptionHidden" name="conditionsDescriptionHidden" value="${cashback.conditionsdescription}" hidden>
                        <!--<input type="text" class="form-control" id="conditionsDescription" name="conditionsDescription" value="" required>-->
                        <textarea class="form-control" id="conditionsDescription" name="conditionsDescription" rows="5" required></textarea>
                    </div>
                </div> 
                
                <div class="form-group row">  
                    <label class="col-sm-2 col-form-label form-label-bg" for="conditionsValidityDescription">Descrizione Condizioni Validit�</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="conditionsValidityDescriptionHidden" name="conditionsValidityDescriptionHidden" value="${cashback.conditionsvaliditydescription}" hidden>
                        <!--<input type="text" class="form-control" id="conditionsValidityDescription" name="conditionsValidityDescription" value="" required>-->
                        <textarea class="form-control" id="conditionsValidityDescription" name="conditionsValidityDescription" rows="5" required></textarea>
                    </div>
                </div> 
            </div>
        </div>
    </div> 
                
    <div class="card mb-3">  
        <div class="card-header"><strong>Anagrafica Accumulo</strong></div>
        <div class="card-body">
            <div class="col-sm">        
                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationDescription">Titolo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationDescriptionHidden" name="accumulationDescriptionHidden" value="${cashback.accumulationdescription}" hidden>
                        <input type="text" class="form-control" id="accumulationDescription" name="accumulationDescription" value="" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationType">Type</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="accumulationType" name="accumulationType" required>
                            <option value="1" ${cashback.accumulationtype == 1 ? 'selected' : ''}>BUY</option>
                            <option value="2" ${cashback.accumulationtype == 2 ? 'selected' : ''}>FLYER</option>
                            <option value="3" ${cashback.accumulationtype == 3 ? 'selected' : ''}>LINES</option>
                            <option value="4" ${cashback.accumulationtype == 4 ? 'selected' : ''}>PDF</option>
                        </select>
                    </div>
                </div>
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationStartDate">Data Inizio Accumulo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationStartDateHidden" name="accumulationStartDateHidden" value="${cashback.accumulationstartdate}" placeholder="dd-mm-yyyy" hidden>
                        <input type="text" class="form-control" id="accumulationStartDate" name="accumulationStartDate" value="" placeholder="dd/mm/yyyy" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationEndDate">Data Fine Accumulo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationEndDateHidden" name="accumulationEndDateHidden" value="${cashback.accumulationenddate}" placeholder="dd-mm-yyyy" hidden>
                        <input type="text" class="form-control" id="accumulationEndDate" name="accumulationEndDate" value="" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>
                    
                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationFlyerPdfLink">Link Flyer Pdf</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationFlyerPdfLinkHidden" name="accumulationFlyerPdfLinkHidden" value="${cashback.accumulationflyerpdflink}" hidden>
                        <input type="text" class="form-control" id="accumulationFlyerPdfLink" name="accumulationFlyerPdfLink" value="">
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationFlyerPdfImageUrl">URL Flyer Pdf Image</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationFlyerPdfImageUrlHidden" name="accumulationFlyerPdfImageUrlHidden" value="${cashback.accumulationflyerpdfimageurl}" hidden>
                        <input type="text" class="form-control" id="accumulationFlyerPdfImageUrl" name="accumulationFlyerPdfImageUrl" value="">
                    </div>
                </div>  
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationProductLines">Linee Prodotto Accumulo</label>
                    <div class="col-sm-3">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="accumulationProductLinesDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Seleziona le Linee Prodotto
                            </button>
                            <div class="dropdown-menu" aria-labelledby="accumulationProductLinesDropdown">
                                <c:forEach var="entry" items="${accumulationLines}">
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="${fn:replace(entry.key, ' ', '_')}" name="accumulationProductLines" value="${entry.key}" ${entry.value == 1 ? 'checked' : ''}>
                                        <label class="form-check-label" for="${fn:replace(entry.key, ' ', '_')}">${entry.key}</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>                    
            </div>
        </div>
    </div>
                    
    <div class="card mb-3">  
        <div class="card-header"><strong>Anagrafica Fruizione</strong></div>
        <div class="card-body">
            <div class="col-sm">        
                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionDescription">Titolo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionDescriptionHidden" name="fruitionDescriptionHidden" value="${cashback.fruitiondescription}" hidden>
                        <input type="text" class="form-control" id="fruitionDescription" name="fruitionDescription" value="" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionType">Type</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="fruitionType" name="fruitionType" required>
                            <option value="1" ${cashback.fruitiontype == 1 ? 'selected' : ''}>BUY</option>
                            <option value="2" ${cashback.fruitiontype == 2 ? 'selected' : ''}>FLYER</option>
                            <option value="3" ${cashback.fruitiontype == 3 ? 'selected' : ''}>LINES</option>
                            <option value="4" ${cashback.fruitiontype == 4 ? 'selected' : ''}>PDF</option>
                        </select>
                    </div>
                </div>
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionStartDate">Data Inizio Fruizione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionStartDateHidden" name="fruitionStartDateHidden" value="${cashback.fruitionstartdate}" placeholder="dd-mm-yyyy" hidden>
                        <input type="text" class="form-control" id="fruitionStartDate" name="fruitionStartDate" value="" placeholder="dd/mm/yyyy" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionEndDate">Data Fine Fruizione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionEndDateHidden" name="fruitionEndDateHidden" value="${cashback.fruitionenddate}" placeholder="dd-mm-yyyy" hidden>
                        <input type="text" class="form-control" id="fruitionEndDate" name="fruitionEndDate" value="" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>
                    
                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionFlyerPdfLink">Link Flyer Pdf</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionFlyerPdfLinkHidden" name="fruitionFlyerPdfLinkHidden" value="${cashback.fruitionflyerpdflink}" hidden>
                        <input type="text" class="form-control" id="fruitionFlyerPdfLink" name="fruitionFlyerPdfLink" value="" >
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionFlyerPdfImageUrl">URL Flyer Pdf Image</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionFlyerPdfImageUrlHidden" name="fruitionFlyerPdfImageUrlHidden" value="${cashback.fruitionflyerpdfimageurl}" hidden>
                        <input type="text" class="form-control" id="fruitionFlyerPdfImageUrl" name="fruitionFlyerPdfImageUrl" value="" >
                    </div>
                </div>  
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionProductLines">Linee Prodotto Fruizione</label>
                    <div class="col-sm-3">
                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" id="fruitionProductLinesDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Seleziona le Linee Prodotto
                            </button>
                            <div class="dropdown-menu" aria-labelledby="fruitionProductLinesDropdown">
                                <c:forEach var="entry" items="${fruitionLines}">
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="${fn:replace(entry.key, ' ', '_')}" name="fruitionProductLines" value="${entry.key}" ${entry.value == 1 ? 'checked' : ''}>
                                        <label class="form-check-label" for="${fn:replace(entry.key, ' ', '_')}">${entry.key}</label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>    
            </div>
        </div>
    </div>
 
    <input type="hidden" id="cashbackId" name="cashbackId" value="${cashback.cashbackid}">
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="button" id="saveCashback" class="btn btn-primary float-right ml-1">Salva</button>   
            <button type="button" id="returnCashback" class="btn btn-secondary float-right ml-1" onclick="goToUrl()">Indietro</button> 
        </div>
    </div>
</form>

<% request.setAttribute("pageJavascript", "/cashback/edit"); %>
