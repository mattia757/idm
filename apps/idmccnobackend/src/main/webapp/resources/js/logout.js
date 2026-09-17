
$("#buttonLogout").click(function() {
    logout();
});

function logout() {
    $.ajax({
        type: "POST",
        url: contextPath+"/logout/",
    });
}