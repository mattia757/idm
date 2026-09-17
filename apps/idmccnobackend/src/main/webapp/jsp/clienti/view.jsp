<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="alert-container-top"></div>
<div class="row">
    <div class="col">
        <ul class="nav mb-3">
            <li class="nav-item">
                <button class="btn btn-primary" onclick="history.back()"><span toggle="" class="fa fa-arrow-left field-icon"></span> Indietro</button>
            </li>
        </ul>
    </div>
    <c:if test="${userProfile.customerCancellation == '1'}">
        <div class="col">
            <ul class="nav mb-3 float-right">
                <li class="nav-item">
                    <c:set var = "searchQuoteValue" value = "'" />
                    <c:set var = "replaceQuoteValue" value = "\\\\\\'" />
                    <c:set var = "userIdParam" value = "${fn:replace(item.userId,searchQuoteValue,replaceQuoteValue)}" />
                    <c:set var = "emailParam" value = "${fn:replace(item.email,searchQuoteValue,replaceQuoteValue)}" />
                    <c:set var = "nameParam" value = "${fn:replace(item.name,searchQuoteValue,replaceQuoteValue)}" />
                    <c:set var = "surnameParam" value = "${fn:replace(item.surname,searchQuoteValue,replaceQuoteValue)}" />
                    <button class="btn btn-primary" onclick="confirmDialog.show('Attenzione', 'Sei sicuro di voler eliminare l\'utenza?', 'deleteUser(\'${userIdParam}\',\'${emailParam}\',\'${nameParam}\',\'${surnameParam}\')')"><span toggle="" class="fa fa-trash trash-icon"></span> Elimina</button>
                </li>
            </ul>
        </div>
    </c:if>
</div>

<div class="card bg-light mb-3">
    <div class="card-header font-weight-bold">Dettaglio Utente</div>
    <div class="card-body">
        <form class="form" role="form">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="userId">Id Utente</label>
                    <input type="text" class="form-control" id="userId" readonly value="${item.userId}">
                </div>
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="type">Tipologia utente</label>
                    <input type="text" class="form-control" id="type" readonly value="${item.type}">
                </div>                
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="name">Nome</label>
                    <input type="text" class="form-control" id="name" readonly value="${item.name}">
                </div>
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="surname">Cognome</label>
                    <input type="text" class="form-control" id="surname" readonly 
                           <c:choose>
                               <c:when test="${item.surname == 'null'}">value="-"</c:when>
                               <c:otherwise>value="${item.surname}"</c:otherwise>
                           </c:choose>
                           >  
                </div>

            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="birthDate">Data di nascita</label>
                    <input type="text" class="form-control" id="birthDate" readonly 
                           <c:choose>
                               <c:when test="${item.birthDate == 'null'}">value="-"</c:when>
                               <c:otherwise>value="${item.birthDate}"</c:otherwise>
                           </c:choose>
                           >
                </div>
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="email">Email</label>
                    <input type="email" class="form-control" id="email" readonly value="${item.email}">
                </div>

            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="emailVerified">Email verificata</label>
                    <input type="text" class="form-control"
                           id="emailVerified" readonly 
                           <c:if test="${item.emailVerified == 'Si'}">value="Verificata" style="background-color:#2ed573 !important; color: #fff !important;"</c:if>
                           <c:if test="${item.emailVerified == 'No'}">value="Non verificata" style="background-color: #ff7f50 !important; color: #fff !important;"</c:if>
                               >
                    </div>
                    <div class="form-group col-md-6">
                        <label class="font-weight-bold" for="coopId">Cooperativa di registrazione</label>
                        <input type="text" class="form-control" id="coopId" readonly 
                        <c:if test="${item.coopId == '1'}">value="Novacoop"</c:if>
                        <c:if test="${item.coopId == '2'}">value="Coop liguria"</c:if>
                        <c:if test="${item.coopId == '3'}">value="Coop Lombardia"</c:if>
                            >
                    </div>


                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label class="font-weight-bold" for="regApplication">Applicazione di registrazione</label>
                        <input type="text" class="form-control" id="regApplication" readonly value="${item.regApplication}">
                </div>
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="regDate">Data di registrazione</label>
                    <input type="text" class="form-control" id="regDate" readonly value="${item.regDate}">
                </div>

            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="status">Stato utente</label>
                    <input type="text" class="form-control" id="status" readonly value="${item.status}">
                </div>
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="gender">Sesso</label>
                    <input type="text" class="form-control" id="gender" readonly value="${item.gender}">
                </div>

            </div>
            <div class="form-row">

                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="resProvince">Provincia di residenza</label>
                    <input type="text" class="form-control" id="resProvince" readonly value="${item.resProvince}">
                </div>
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="resCity">Citt&agrave; di residenza</label>
                    <input type="text" class="form-control" id="resCity" readonly value="${item.resCity}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="pdvId">Id Store Locator</label>
                    <input type="text" class="form-control" id="pdvId" readonly value="${item.pdvId}">
                </div>
                <div class="form-group col-md-6">
                    <label class="font-weight-bold" for="codicePdv">Punto vendita</label>
                    <input type="text" class="form-control" id="codicePdv" readonly value="${item.codicePdv}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <c:if test="${userProfile.role == 'ADMIN' || userProfile.role == 'CCNO' || userProfile.role == 'MODIFY'}">
                        <button type="button" class="btn btn-primary btn-md btn-block" onclick="confirmDialog.show('Attenzione', 'Sei sicuro di voler inviare il codice di verifica email?', 'sendEmailAddressVerificationCode(\'${item.email}\', \'${item.coopId}\')')">Invia codice verifica email</button>
                    </c:if>
                </div>
                <div class="form-group col-md-6">
                    <c:if test="${userProfile.role == 'ADMIN' || userProfile.role == 'CCNO' || userProfile.role == 'MODIFY'}">
                        <button type="button" class="btn btn-primary btn-md btn-block" onclick="confirmDialog.show('Attenzione', 'Sei sicuro di voler inviare il codice di reset password?', 'sendResetPasswordCode(\'${item.email}\', \'${item.coopId}\')')">Invia reset password</button>
                    </c:if>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="card bg-light mb-3">
    <div class="card-header font-weight-bold">Accessi e stato Utente</div>
    <div class="card-body">
        <form class="form" role="form">
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="firstAppAccessDate">Primo accesso da appCoop</label>
                    <input type="text" class="form-control" id="firstAppAccessDate" readonly value="${item.firstAppAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="lastAppAccessDate">Ultimo accesso da appCoop</label>
                    <input type="text" class="form-control" id="lastAppAccessDate" readonly value="${item.lastAppAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="userDisableApp">Disabilitato su appCoop</label>
                    <input type="text" class="form-control" id="userDisableApp" readonly value="${item.userDisableApp}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="firstEcommerceAccessDate">Primo accesso da ecommerce</label>
                    <input type="text" class="form-control" id="firstEcommerceAccessDate" readonly value="${item.firstEcommerceAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="lastEcommerceAccessDate">Ultimo accesso da ecommerce</label>
                    <input type="text" class="form-control" id="lastEcommerceAccessDate" readonly value="${item.lastEcommerceAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="userDisableEcommerce">Disabilitato su ecommerce</label>
                    <input type="text" class="form-control" id="userDisableEcommerce" readonly value="${item.userDisableEcommerce}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="firstEventiInteressiAccessDate">Primo accesso da portale partecipazione interessi</label>
                    <input type="text" class="form-control" id="firstEventiInteressiAccessDate" readonly value="${item.firstEventiInteressiAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="lastEventiInteressiAccessDate">Ultimo accesso da portale partecipazione interessi</label>
                    <input type="text" class="form-control" id="lastEventiInteressiAccessDate" readonly value="${item.lastEventiInteressiAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="userDisableEventiInteressi">Disabilitato su portale partecipazione interessi</label>
                    <input type="text" class="form-control" id="userDisableEventiInteressi" readonly value="${item.userDisableEventiInteressi}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="firstPortalAccessDate">Primo accesso da portale coop.it</label>
                    <input type="text" class="form-control" id="firstPortalAccessDate" readonly value="${item.firstPortalAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="lastPortalAccessDate">Ultimo accesso da portale coop.it</label>
                    <input type="text" class="form-control" id="lastPortalAccessDate" readonly value="${item.lastPortalAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="userDisablePortal">Disabilitato su portale coop.it</label>
                    <input type="text" class="form-control" id="userDisablePortal" readonly value="${item.userDisablePortal}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="firstPortalNovaAccessDate">Primo accesso da portale Nova</label>
                    <input type="text" class="form-control" id="firstPortalNovaAccessDate" readonly value="${item.firstPortalNovaAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="lastPortalNovaAccessDate">Ultimo accesso da portale Nova</label>
                    <input type="text" class="form-control" id="lastPortalNovaAccessDate" readonly value="${item.lastPortalNovaAccessDate}">
                </div>
                <div class="form-group col-md-4">
                    <label class="font-weight-bold" for="userDisablePortalNova">Disabilitato su portale Nova</label>
                    <input type="text" class="form-control" id="userDisablePortalNova" readonly value="${item.userDisablePortalNova}">
                </div>
            </div>
        </form>
    </div>
</div>
</div>
<div class="card bg-light mb-3">
    <div class="card-header font-weight-bold">Privacy</div>
    <div class="card-body">
        <div class="row">
            <c:forEach items="${item.userPrivacyList}" var="privacy"> 
                <div class="col-sm-4">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col" <c:if test="${privacy.optins.size() < 1}">colspan="2"</c:if><c:if test="${privacy.optins.size() >= 1}">colspan="${privacy.optins.size()}"</c:if> class="text-center">${privacy.coop}</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:if test="${privacy.optins.size() < 1}">
                                <tr>
                                    <td class="text-light text-center" width="50%">
                                        <span class="badge badge-pill badge-secondary">n</span></i>
                                    </td>
                                    <td class="text-light text-center" width="50%">
                                        <span class="badge badge-pill badge-secondary">n</span></i>
                                    </td>
                                </tr>   
                            </c:if>
                            <tr>
                                <c:set var="count" value="0" scope="page" />
                                <c:forEach items="${privacy.optins}" var="optin">
                                    <c:set var="count" value="${count + 1}" scope="page"/>
                                    <td width="50%" class="text-center">
                                        <c:if test="${optin.privacyType == 1}">Consenso Profilazione 4.B</c:if>
                                        <c:if test="${optin.privacyType == 2}">Consenso Marketing 4.C</c:if>
                                        <!-- Privacy ${count} -->
                                    </td>
                                </c:forEach>
                            <tr>
                                <c:forEach items="${privacy.optins}" var="optin">
                                    <td width="50%" class="text-light text-center"
                                        <c:if test="${optin.value == null}">style="background-color: #a4b0be !important; color: #fff !important;"</c:if>
                                        <c:if test="${optin.value == true}">style="background-color: #2ed573 !important; color: #fff !important;"</c:if>
                                        <c:if test="${optin.value == false}">style="background-color: #ff7f50 !important; color: #fff !important;"</c:if>
                                            >
                                        <c:if test="${optin.value == null}">Non espressa</c:if>
                                        <c:if test="${optin.value == true}">Accettata</c:if>
                                        <c:if test="${optin.value == false}">Negata</c:if>
                                        </td>
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>   
                </div>
            </c:forEach>
        </div>
        <div class="row">
            <div class="form-group col-md-4">
                <label class="font-weight-bold" for="lastPrivacyUpdate">Data/ora ultimo aggiornamento privacy</label>
                <input type="text" class="form-control" id="lastPrivacyUpdate" readonly value="${item.lastPrivacyUpdate}">
            </div>
        </div>
    </div> 
</div>
<div id="alert-container"></div>
<div class="card bg-light mb-3">
    <div class="card-header font-weight-bold">Tessere socio</div>
    <div class="card-body">
        <div>
            <c:choose>
                <c:when test="${item.eanCards.size() >= 1}">        
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="col" data-field="eanCard">N&deg; tessera</th> 
                                <th class="col" data-field="coopId" id="nomePdv">Cooperativa</th>
                                    <c:if test="${userProfile.role == 'ADMIN' || userProfile.role == 'CCNO' || userProfile.role == 'MODIFY'}">
                                    <th class="col" data-field="actions">Verifica</th>
                                    <th class="col" data-field="actions">Elimina</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${item.eanCards}" var="eanCard">
                                <tr>
                                    <td>${eanCard.eanCard}</td>
                                    <td><c:if test="${eanCard.coopId == '1'}">Novacoop</c:if>
                                        <c:if test="${eanCard.coopId == '2'}">Liguria</c:if>
                                        <c:if test="${eanCard.coopId == '3'}">Lombardia</c:if>
                                        </td>
                                    <c:if test="${userProfile.role == 'ADMIN' || userProfile.role == 'CCNO' || userProfile.role == 'MODIFY'}">
                                        <td width="5%" class="text-center bg-primary"><a href="javascript:void(0);" onclick="verifyLoyaltyInfo('${eanCard.eanCard}', '${item.birthDate}', '${eanCard.coopId}')" title="Verifica" style="display: block; width: 100%;"><i class="fa fa-spinner text-white"></i></a></td>
                                        <td width="5%" class="text-center bg-primary"><a href="javascript:void(0);" onclick="confirmDialog.show('Attenzione', 'Sei sicuro di voler eliminare la tessera socio dal profilo?', 'removeCard(\'${item.userId}\', \'${eanCard.eanCard}\', \'${item.birthDate}\', \'${eanCard.coopId}\')')" title="Rimuovi" style="display: block; width: 100%;"><i class="fas fa-times text-white"></i></a></td>
                                            </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="text-center">Nessuna tessera associata</p>
                </c:otherwise>
            </c:choose> 
        </div>
        <c:if test="${userProfile.role == 'ADMIN' || userProfile.role == 'CCNO' || userProfile.role == 'MODIFY'}">
            <div class="form-group row">
                <div class="col">

                    <button type="button" class="btn btn-primary btn-md btn-block" data-toggle="modal" data-target="#staticBackdrop">
                        Aggiungi nuova tessera
                    </button>

                </div>
            </div>
        </c:if>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="staticBackdrop" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Aggiungi tessera socio</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form role="form">
                        <div class="form-group row">
                            <div class="col">
                                <input type="hidden" id="userId" name="userId" value="${item.userId}">
                                <input type="hidden" id="birthDate" name="birthDate" value="${item.birthDate}">
                                <input type="hidden" id="coopId" name="coopId" value="${item.coopId}">
                                <label for="eanCard">N. tessera</label>
                                <input type="text" class="form-control" id="eanCard" name="eanCard" placeholder="Inserisci numero tessera" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col">
                                <button type="button" class="btn btn-secondary float-right ml-1" data-dismiss="modal">Chiudi</button>
                                <button type="button" id="addCardButton" class="btn btn-primary float-right ml-1">Aggiungi</button>

                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<% request.setAttribute("pageJavascript", "/clienti/view");%>
