<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/resources/vendor/jquery/jquery.min.js" />"></script>
<script src="<c:url value="/resources/vendor/jquery/jquery-migrate-1.4.1.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/moment-with-locales.min.js" />"></script>
            
<!-- Core plugin JavaScript-->
<script src="<c:url value="/resources/vendor/jquery-easing/jquery.easing.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap-datetimepicker.min.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap-waitingfor.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap-confirm.js" />"></script>
<script src="<c:url value="/resources/vendor/bootstrap/js/bootstrap-info.js" />"></script>
            
<!-- Page level plugin JavaScript-->
<script src="<c:url value="/resources/vendor/datatables/jquery.dataTables.js" />"></script>
<script src="<c:url value="/resources/vendor/datatables/dataTables.bootstrap4.js" />"></script>
<script src="<c:url value="/resources/vendor/datatables/dataTable.date-it.js" />"></script>

<script src="<c:url value="/resources/vendor/datatables/moment/moment.min.js" />"></script>
<script src="<c:url value="/resources/vendor/datatables/moment/datetime-moment.js" />"></script>
            
<!-- Custom scripts for all pages-->
<script src="<c:url value="/resources/js/sb-admin.min.js" />"></script>
<script src="<c:url value="/resources/js/custom.js.jsp" />"></script>
<script src="<c:url value="/resources/js/custom.js?v=ui-2" />"></script>

<!-- scripts for logout-->
<script src="<c:url value="/resources/js/logout.js" />"></script>
<!-- scripts for updatePassword-->
<script src="<c:url value="/resources/js/updatePassword.js" />"></script>

            
<!-- custom page script -->
<c:if test = "${not empty pageJavascript}">
    <c:url var="pageJavascriptUrl" value="/resources/js${pageJavascript}.js" />
    <c:choose>
        <c:when test="${not empty pageJavascriptVersion}">
            <c:set var="pageJavascriptUrl" value="${pageJavascriptUrl}?v=${pageJavascriptVersion}" />
        </c:when>
        <c:otherwise>
            <c:set var="pageJavascriptUrl" value="${pageJavascriptUrl}?v=ui-6" />
        </c:otherwise>
    </c:choose>
    <script src="${pageJavascriptUrl}"></script>
</c:if>
