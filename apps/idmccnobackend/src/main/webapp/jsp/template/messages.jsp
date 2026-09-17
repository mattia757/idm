<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger"><c:out value="${errorMessage}" /></div>
</c:if>
<c:if test="${not empty successMessage}">
    <div class="alert alert-success"><c:out value="${successMessage}" /></div>
</c:if>
