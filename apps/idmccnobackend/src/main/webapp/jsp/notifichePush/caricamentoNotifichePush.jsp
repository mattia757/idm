<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="alert-container"></div>

<form name="form-caricamento" class="form needs-validation" role="form" method="POST" novalidate enctype="multipart/form-data" modelAttribute="uplFileNotifichePush" autocomplete="off">
    <div class="card mb-3">  
        <div class="card-header">Caricamento Lista Per Notifiche Push</div>
        <div class="card-body">
                <div class="col-sm">              
                    <div class="form-group row">                   
                        <div class="col-sm-3">
                            <input type="file" class="filestyle" data-icon="false" title="Seleziona il file" id="uplFileNotifichePush" name="uplFileNotifichePush" required>                           
                        </div>
                        <div class="col-sm-1">
                            <button type="button" class="btn btn-primary float-right ml-1" onclick="caricamentoListaNotifichePush()">Carica</button>   
                        </div>
                        <div class="col-sm-1">
                            <button type="button" class="btn btn-success" onclick="goToUrl('<c:url value="/resources/xls/ModelListPushNotification.xlsx"/>')">Modello</button>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</form>

<% request.setAttribute("pageJavascript", "/notifichePush/caricamentoNotifichePush");%>


