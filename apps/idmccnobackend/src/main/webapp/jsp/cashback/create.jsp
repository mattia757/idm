<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="alert-container"></div>

<form name="form" class="form needs-validation" role="form" novalidate>
   
    <div class="card mb-3">  
        <div class="card-header"><strong>Anagrafica Cashback</strong></div>
        <div class="card-body">
            <div class="col-sm">              
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="id">Cashback Id</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control-plaintext" readonly id="id" value="***">
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackTitle">Titolo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="cashbackTitle" name="cashbackTitle" required>
                    </div>
                </div>     

                <div class="form-group row"> 
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackDescription">Descrizione</label>
                    <div class="col-sm-8">
                        <!--<input type="text" class="form-control" id="cashbackDescription" name="cashbackDescription" required>-->
                        <textarea class="form-control" id="cashbackDescription" name="cashbackDescription" rows="5" required></textarea>
                    </div>
                </div>
                
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackValue">Valore</label>
                    <div class="col-sm-3">
                        <input type="number" class="form-control" id="cashbackValue" name="cashbackValue" max="999999999" required >
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="status">Attivo</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="status" name="status" required>
                            <option value="1" ${cashback.status == 1 ? 'selected' : ''}>Attivo</option>
                            <option value="0" ${cashback.status == 0 ? 'selected' : ''}>Non attivo</option>
                        </select>
                    </div>
                </div> 
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackPublicationStartDate">Data Inizio Pubblicazione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="cashbackPublicationStartDate" name="cashbackPublicationStartDate" placeholder="dd/mm/yyyy" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="cashbackPublicationEndDate">Data Fine Pubblicazione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="cashbackPublicationEndDate" name="cashbackPublicationEndDate" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>
                    
                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="conditionsDescription">Descrizione Condizioni</label>
                    <div class="col-sm-8">
                        <!--<input type="text" class="form-control" id="conditionsDescription" name="conditionsDescription" required>-->
                        <textarea class="form-control" id="conditionsDescription" name="conditionsDescription" rows="5" required></textarea>
                    </div>
                </div>    
                        
                <div class="form-group row"> 
                    <label class="col-sm-2 col-form-label form-label-bg" for="conditionsValidityDescription">Descrizione Condizioni Validità</label>
                    <div class="col-sm-8">
                        <!--<input type="text" class="form-control" id="conditionsValidityDescription" name="conditionsValidityDescription" required>-->
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
                        <input type="text" class="form-control" id="accumulationDescription" name="accumulationDescription" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationType">Type</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="accumulationType" name="accumulationType" required>
                            <option value="1">BUY</option>
                            <option value="2">FLYER</option>
                            <option value="3">LINES</option>
                            <option value="4">PDF</option>
                        </select>
                    </div>
                </div>
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationStartDate">Data Inizio Accumulo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationStartDate" name="accumulationStartDate" placeholder="dd/mm/yyyy" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationEndDate">Data Fine Accumulo</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationEndDate" name="accumulationEndDate" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>
                    
                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationFlyerPdfLink">Link Flyer Pdf</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationFlyerPdfLink" name="accumulationFlyerPdfLink">
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="accumulationFlyerPdfImageUrl">URL Flyer Pdf Image</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="accumulationFlyerPdfImageUrl" name="accumulationFlyerPdfImageUrl">
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
                        <input type="text" class="form-control" id="fruitionDescription" name="fruitionDescription"required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionType">Type</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="fruitionType" name="fruitionType" required>
                            <option value="1">BUY</option>
                            <option value="2">FLYER</option>
                            <option value="3">LINES</option>
                            <option value="4">PDF</option>
                        </select>
                    </div>
                </div>
                    
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionStartDate">Data Inizio Fruizione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionStartDate" name="fruitionStartDate" placeholder="dd/mm/yyyy" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionEndDate">Data Fine Fruizione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionEndDate" name="fruitionEndDate" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>
                    
                <div class="form-group row">                   
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionFlyerPdfLink">Link Flyer Pdf</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionFlyerPdfLink" name="fruitionFlyerPdfLink">
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="fruitionFlyerPdfImageUrl">URL Flyer Pdf Image</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="fruitionFlyerPdfImageUrl" name="fruitionFlyerPdfImageUrl">
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
 
    <div class="form-group row">
        <div class="col-sm-10">
            <button type="button" id="saveAnagCashback" class="btn btn-primary float-right ml-1">Salva</button>
        </div>
    </div>
</form>

<% request.setAttribute("pageJavascript", "/cashback/edit"); %>
