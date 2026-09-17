<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Breadcrumbs-->
<ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="<c:url value="${pageLink}" ></c:url>">${section}</a></li>
    <li class="breadcrumb-item active">${subsection}</li>
</ol>
    