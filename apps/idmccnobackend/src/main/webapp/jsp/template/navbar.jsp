<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
    
    <a class="mr-1" href="<c:url value="/" />"><img src="<c:url value="/resources/img/brand_coop.png" ></c:url>" class="d-none d-lg-inline" alt="" title=""/></a>
    <a class="navbar-brand mr-1 nav-link" href="<c:url value="/" ></c:url>">IDM CCNO Backend</a>
    <span class="navbar-text small text-white-50 mr-2">v${initParam.appVersion}</span>
    
    <button class="btn btn-link btn-sm text-white order-1 order-sm-0" id="sidebarToggle" href="#">
        <i class="fas fa-bars"></i>
    </button>
    
    <!-- Navbar -->
    <ul class="navbar-nav ml-auto mr-md-0">
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${userProfile.username} - ${userProfile.nomeCooperativa}</span><i class="fas fa-user-circle fa-fw"></i>
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <!--
                <a class="dropdown-item" href="#">Settings</a>
                <a class="dropdown-item" href="#">Activity Log</a>
                <div class="dropdown-divider"></div>
                -->
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">Logout</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#newPasswordModal">Cambia password</a>
            </div>
        </li>
    </ul>
    
</nav>
