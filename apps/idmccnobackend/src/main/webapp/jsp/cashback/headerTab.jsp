<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--<div>
    <div class="row justify-content-center"> 
        <div class="card">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link <c:if test="${tabSelected == '0'}">active</c:if>" href="javascript:void(0);" onclick="goToUrl('<c:url value="/cashback/edit"><c:param name="id" value="${cashback.cashbackid}" /></c:url>')">Anagrafica</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${tabSelected == '1'}">active</c:if>" href="javascript:void(0);" onclick="goToUrl('<c:url value="/cashback/editPdv"><c:param name="id" value="${cashback.cashbackid}" /></c:url>')">PDV Associati</a>
                </li>
            </ul>         
        </div>              
    </div>     
</div>  -->

<div>
    <div class="row justify-content-center"> 
        <div class="card">
            <ul class="nav nav-tabs">
                <li class="nav-item">
                    <a class="nav-link <c:if test="${tabSelected == '0'}">active</c:if>" href="javascript:void(0);" onclick="navigateToUrl('<c:url value="/cashback/edit"><c:param name="id" value="${cashback.cashbackid}" /></c:url>')">Anagrafica</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${tabSelected == '1'}">active</c:if>" href="javascript:void(0);" onclick="navigateToUrl('<c:url value="/cashback/editPdv"><c:param name="id" value="${cashback.cashbackid}" /></c:url>')">PDV Associati</a>
                </li>
            </ul>         
        </div>              
    </div>     
</div>  

<div id="overlay" class="overlay"></div>

<script>
    function navigateToUrl(url) {
        blockScreen();
        window.location.href = url;
    }

    // Nascondi lo screen di overlay quando la pagina � completamente caricata
    window.onload = function() {
        unblockScreen();
    };
</script>



