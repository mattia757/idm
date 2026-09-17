<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
    
    <head>
        
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="IDM CCNO Backend ">
        <meta name="author" content="NTT DATA Italia">
        
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />" />
        
        <title>Coop - IDM CCNO Backend</title>
        
        <jsp:include page="cssimport.jsp" />
        
    </head>
    
    <body class="bg-dark">
        
        <jsp:include page="navbarLogin.jsp" />
        
        <div class="container">
            <jsp:include page="messages.jsp" />
            <% pageContext.include((String)request.getAttribute("page") + ".jsp"); %>
        </div>
        
        <jsp:include page="jsimport.jsp" />
        
        <!-- Modal spinner in overlay -->
        <div class="loading" style="display:none;">Loading&#8230;</div>
        
    </body>
    
</html>
