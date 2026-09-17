<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
    sessionStorage.clear();
</script>
<div id="alert-container"></div>
<div class="card card-login mx-auto mt-5">
    <div class="card-header">Login</div>
    <div class="card-body">
        <form name="form"  role="form" class="form needs-validation" action="<c:url value="/login/auth" />" method="post" modelAttribute="userLoginDto">
            <div class="form-group">
                <div class="form-label-group">
                    <input type="text" id="username" name="username" class="form-control" placeholder="Username" required="required" autofocus="autofocus">
                    <label for="username">Username</label>
                </div>
            </div>
            <div class="form-group">
                <div class="form-label-group">
                    <input type="password" id="password" name="password" class="form-control" placeholder="Password" required="required">
                    <label for="password">Password</label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Login</button>
            <!--<input class="btn btn-primary btn-block"type="submit" />-->
           
        </form>
    </div>
</div>
