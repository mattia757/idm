<!-- Logout Modal-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Logout</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">x</span>
                </button>
            </div>
            
            <form name="form"  role="form" class="form needs-validation" action="<c:url value="/logout/" />" method="post">
                <div class="modal-body">
                    Clicca su "Logout" per chiudere la sessione.
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary float-right ml-1" data-dismiss="modal">Chiudi</button>
                    <button type="submit" class="btn btn-primary float-right ml-1">Logout</button>
                </div>
            </form>           
        </div>
    </div>
</div>