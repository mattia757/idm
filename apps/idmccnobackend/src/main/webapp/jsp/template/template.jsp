<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
    
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="IDM CCNO Backend">
        <meta name="author" content="NTT DATA Italia">
        <meta name="csrf-token" content="${sessionScope.csrfToken}">
        
        <link rel="shortcut icon" href="<c:url value="/resources/img/favicon.ico" />" />
        
        <title>Coop - IDM CCNO Backend</title>
        
        <jsp:include page="cssimport.jsp" />
        
    </head>
    
    <body id="page-top">
                
        <jsp:include page="navbar.jsp" />
        
        <div id="wrapper">
            
            <jsp:include page="sidebar.jsp" />
            
            <div id="content-wrapper">
                
                <div class="container-fluid">
                    
                    <jsp:include page="breadcrumbs.jsp" />
                    
                    <jsp:include page="messages.jsp" />
                    
                    <% pageContext.include((String)request.getAttribute("page") + ".jsp"); %>
                    
                </div>
                <!-- /.container-fluid -->
                
               <jsp:include page="footer.jsp" />
                
            </div>
            <!-- /#content-wrapper -->
            
        </div>
        <!-- /#wrapper -->
        
        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        
        <jsp:include page="logout.jsp" />
        
        <jsp:include page="newPasswordModal.jsp" />
        
        <jsp:include page="jsimport.jsp" />
        
        <!-- Modal spinner in overlay -->
        <div class="loading" style="display:none;">Loading&#8230;</div>
        
    </body>
    
</html>
