<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
  /* verde quando attivo */
  .custom-control-input:checked ~ .custom-control-label::before {
    background-color: #28a745;
    border-color: #28a745;
  }
  /* rosso quando inattivo */
  .custom-control-input:not(:checked) ~ .custom-control-label::before {
    background-color: #dc3545;
    border-color: #dc3545;
  }
  .custom-control-label::after { background-color: #fff !important; }
  .voucher-status-button[disabled] {
    cursor: default;
    opacity: 1;
  }
</style>


<div id="alert-container"></div>

<form name="form" class="form needs-validation" role="form">    

    <!-- Blocco Anagrafica CRM (Non modificabile) -->
    <div class="card mb-3">  
        <div class="card-header"><strong>Anagrafica CRM Voucher</strong></div>
        <div class="card-body">
            <div class="col-sm">              
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="idVoucher">Voucher Id</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control-plaintext" id="idVoucher" name="idVoucher" value="${voucher.idVoucher}" readonly>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="idType">Id Type</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control-plaintext" id="idType" name="idType" value="${voucher.idType}" readonly>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="voucherType">Voucher Type</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control-plaintext" id="voucherType" name="voucherType" value="${voucher.voucherType}" readonly>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="coupCounter">Coup Counter</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control-plaintext" id="coupCounter" name="coupCounter" value="${voucher.coupCounter}" readonly>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="idStateFk">Id State</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control-plaintext" id="idStateFk" name="idStateFk" value="${voucher.idStateFk}" readonly>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Blocco Anagrafica Modificabile -->
    <div class="card mb-3">        
        <div class="card-header"><strong>Anagrafica Voucher</strong></div>
        <div class="card-body">
            <div class="col-sm">
                <!-- Stato Voucher (modificabile) -->
                <div class="form-group row align-items-center">
                    <label class="col-sm-2 col-form-label form-label-bg" for="activeSwitch">Stato Voucher</label>
                    <div class="col-sm-8 d-flex align-items-center">
                        <div class="custom-control custom-switch">
                            <input type="checkbox"
                                   class="custom-control-input"
                                   id="activeSwitch"
                                   aria-label="Attiva/Disattiva voucher"
                                   ${voucher.attivo == 'ATTIVO' ? 'checked' : ''}>
                            <label class="custom-control-label" for="activeSwitch"></label>
                        </div>

                        <!-- Indicatore dinamico, con lo stesso stile dei pulsanti della pagina -->
                        <button id="activeBadge" type="button" disabled
                              class="btn voucher-status-button ml-3 ${voucher.attivo == 'ATTIVO' ? 'btn-success' : 'btn-danger'}">
                            ${voucher.attivo == 'ATTIVO' ? 'Attivo' : 'Inattivo'}
                        </button>

                        <!-- Valore postato al backend -->
                        <input type="hidden" id="attivo" name="attivo"
                               value="${empty voucher.attivo ? 'INATTIVO' : voucher.attivo}" />
                    </div>
                </div>
                
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="headerMsg">Header Msg</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" id="headerMsg" name="headerMsg" rows="2" required>${voucher.headerMsg}</textarea>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="codice">Codice</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="codice" name="codice" maxlength="255" value="${fn:escapeXml(voucher.codice)}">
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="link">Link</label>
                    <div class="col-sm-3">
                        <input type="url" class="form-control" id="link" name="link" maxlength="2048" value="${fn:escapeXml(voucher.link)}" placeholder="https://">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="bcdEanDiscCoup8">EAN 8</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="bcdEanDiscCoup8" name="bcdEanDiscCoup8" maxlength="10" value="${voucher.bcdEanDiscCoup8}" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="valueType">Value Type</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="valueType" name="valueType" maxlength="50" value="${voucher.valueType}" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="value">Value</label>
                    <div class="col-sm-3">
                        <input type="number" class="form-control" id="value" name="value" max="999999999" step="0.01" value="${voucher.value}" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="commType">Comm Type</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="commType" name="commType" required>
                            <option value="21" ${voucher.commType == '21' ? 'selected' : ''}>Attivo con barcode</option>
                            <option value="22" ${voucher.commType == '22' ? 'selected' : ''}>Attivo su carta</option>
                        </select>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="redStartDate">Data Inizio Redenzione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="redStartDate" name="redStartDate" value="${voucher.redStartDate}" placeholder="dd/mm/yyyy" required>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="redEndDate">Data Fine Redenzione</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="redEndDate" name="redEndDate" value="${voucher.redEndDate}" placeholder="dd/mm/yyyy" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="commDescr">Descrizione Commerciale</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="commDescr" name="commDescr" value="${voucher.commDescr}" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="redPromoType">Red Promo Type</label>
                    <div class="col-sm-3">
                        <select class="form-control" id="redPromoType" name="redPromoType" required>
                            <c:forEach var="option" items="${promoTypes}">
                                <option value="${option.codice}" ${option.codice == voucher.redPromoType ? 'selected' : ''}>${option.descrizione}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="col-sm-2 col-form-label form-label-bg" for="tag">Tag</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="tag" name="tag" value="${voucher.tag}" maxlength="100">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="descrizioneWeb">Descrizione Web</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" id="descrizioneWeb" name="descrizioneWeb" rows="4">${fn:escapeXml(voucher.descrizioneWeb)}</textarea>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="messaggioCarta">Messaggio su Carta</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" id="messaggioCarta" name="messaggioCarta" rows="3">${fn:escapeXml(voucher.messaggioCarta)}</textarea>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="termsAndConditions">Termini e Condizioni</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" id="termsAndConditions" name="termsAndConditions" rows="6">${fn:escapeXml(voucher.termsAndConditions)}</textarea>
                    </div>
                </div>                    
                    
            </div>
        </div>
    </div>

    <div class="card mb-3">
        <div class="card-header"><strong>Immagine Voucher</strong></div>
        <div class="card-body">
            <div class="col-sm">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label form-label-bg" for="voucherImage">Seleziona Immagine</label>
                    <div class="col-sm-8">
                        <input type="file" class="filestyle" data-icon="false" title="Scegli immagine" id="voucherImage" name="voucherImage" accept=".png,image/png">
                        <small class="form-text text-muted">Formato consentito: PNG.</small>
                        <c:if test="${not empty voucher.couponImageUrl}">
                            <div class="mt-3">
                                <img src="${fn:escapeXml(voucher.couponImageUrl)}" alt="Immagine voucher" class="img-fluid" style="max-height: 240px; border: 1px dotted #ced4da;">
                                <div>
                                    <a href="${fn:escapeXml(voucher.couponImageUrl)}" target="_blank" rel="noopener noreferrer">Apri immagine corrente</a>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="form-group row">
        <div class="col-sm-10">
            <button type="submit" id="saveCoupon" class="btn btn-primary float-right ml-1">Salva</button>   
            <button type="button" id="returnCoupon" class="btn btn-secondary float-right ml-1" onclick="goToUrl()">Indietro</button> 
        </div>
    </div>
</form>

<%
    request.setAttribute("pageJavascript", "/couponing/edit");
    request.setAttribute("pageJavascriptVersion", "20260820-2");
%>
