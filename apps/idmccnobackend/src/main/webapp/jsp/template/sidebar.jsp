<%@page import="com.nttdata.idmccnobe.util.Constants"%>
<%@page import="com.nttdata.idmccnobe.dto.UserProfile"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
UserProfile userProfile = (UserProfile) request.getAttribute(Constants.USER_PROFILE);
%>

<!-- Sidebar -->
<ul class="sidebar navbar-nav">
    <!--<li class="nav-item active">
        <a class="nav-link" href="<c:url value="/" />">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span>
        </a>
    </li>-->
    <% if (userProfile!=null) {%>
        <li class="nav-item active mt-5">
            <a class="nav-link" href="<c:url value="/clienti/" ></c:url>">
                <i class="fas fa-fw fa-folder"></i>
                <span>Clienti</span>
            </a>
        </li>
        <c:if test="${userProfile.role == 'ADMIN'}">
        <!-- export utenti disponibile solo per super amministratore ADMIN -->
        <li class="nav-item active">
            <a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-fw fa-folder"></i>
                <span>Export</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                <a class="dropdown-item" href="<c:url value="/exportExcel/utenti" ></c:url>">Anagrafica Utenti</a>
                <!--<a class="dropdown-item" href="<c:url value="/exportExcel/utentiInattivi" ></c:url>">Utenti inattivi</a>-->
                <!--<a class="dropdown-item" href="<c:url value="/exportExcel/utentiCancellati" ></c:url>">Utenti cancellati</a>-->
            </div>
        </li>
        </c:if>
        <c:if test="${userProfile.role == 'ADMIN'}">
        <!-- export utenti disponibile solo per super amministratore ADMIN -->
        <li class="nav-item active">
            <a class="nav-link" href="<c:url value="/caricamento/notifichePush" ></c:url>">
                <i class="fas fa-fw fa-folder"></i>
                <span>Notifiche Push</span>
            </a>
        </li>
        </c:if>
        <c:if test="${userProfile.role == 'ADMIN'}">
        <!-- export utenti disponibile solo per super amministratore ADMIN -->
        <li class="nav-item active">
            <a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-fw fa-folder"></i>
                <span>Cashback</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                <a class="dropdown-item" href="<c:url value="/cashback/ricerca" ></c:url>">Ricerca</a>
                <a class="dropdown-item" href="<c:url value="/cashback/crea" ></c:url>">Crea cashback</a>
            </div>
        </li>
        </c:if>
        <c:if test="${userProfile.role == 'ADMIN'}">
        <!-- export utenti disponibile solo per super amministratore ADMIN -->
        <li class="nav-item active">
            <a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-fw fa-folder"></i>
                <span>Couponing</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                <a class="dropdown-item" href="<c:url value="/couponing/ricerca" ></c:url>">Ricerca</a>
            </div>
        </li>
        </c:if>
        <c:if test="${userProfile.role == 'ADMIN'}">
        <li class="nav-item active">
            <a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-fw fa-folder"></i>
                <span>Partecipazioni</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                <a class="dropdown-item" href="<c:url value="/partecipazioni/ricerca" ></c:url>">Ricerca</a>
            </div>
        </li>
        </c:if>
        <c:if test="${userProfile.role == 'ADMIN'}">
        <!-- gestione utenti disponibile solo per super amministratore ADMIN -->
        <li class="nav-item active">
            <a class="nav-link dropdown-toggle" id="pagesDropdown" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-fw fa-folder"></i>
                <span>Utenze Backend</span>
            </a>
            <div class="dropdown-menu" aria-labelledby="pagesDropdown">
                <a class="dropdown-item" href="<c:url value="/usersBe/" ></c:url>">Ricerca</a>
                <a class="dropdown-item" href="<c:url value="/usersBe/create" ></c:url>">Crea nuova utenza</a>
            </div>
        </li>
        </c:if>
    <% } %>
</ul>
    
