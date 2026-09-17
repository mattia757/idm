$.ajaxSetup({
    beforeSend: function(xhr) {
        var csrfToken = getCsrfToken();
        if (csrfToken) {
            xhr.setRequestHeader("X-CSRF-Token", csrfToken);
        }
    },
    error: function(jqXHR, exception) {
        unblockScreen();
        if (isSessionExpiredResponse(jqXHR)) {
            handleSessionExpired();
        } else if (isCsrfInvalidResponse(jqXHR)) {
            handleCsrfInvalid();
        } else {
            showServiceUnavailableMessage();
        }
    }
});

$(function() {
    // Intercetta anche le richieste che definiscono un callback error locale.
    $(document).ajaxError(function(event, jqXHR) {
        if (isSessionExpiredResponse(jqXHR)) {
            handleSessionExpired();
        } else if (isCsrfInvalidResponse(jqXHR)) {
            handleCsrfInvalid();
        }
    });

    $("form[method='post'], form[method='POST']").each(function() {
        addCsrfTokenToForm(this);
    });

    $(".alert-success").each(function() {
        scheduleSuccessAlertDismissal($(this));
    });
});

function getCsrfToken() {
    return $("meta[name='csrf-token']").attr("content");
}

function addCsrfTokenToForm(form) {
    var csrfToken = getCsrfToken();
    if (csrfToken && $(form).find("input[name='csrfToken']").length === 0) {
        $("<input>", { type: "hidden", name: "csrfToken", value: csrfToken }).appendTo(form);
    }
}

function isSessionExpiredResponse(jqXHR) {
    if (!jqXHR) {
        return false;
    }
    if (jqXHR.status === 401) {
        return true;
    }
    try {
        var response = jqXHR.responseJSON || JSON.parse(jqXHR.responseText || "{}");
        return response && response.error === "SESSION_EXPIRED";
    } catch (e) {
        return false;
    }
}

function isCsrfInvalidResponse(jqXHR) {
    if (!jqXHR) {
        return false;
    }
    try {
        var response = jqXHR.responseJSON || JSON.parse(jqXHR.responseText || "{}");
        return response && response.error === "CSRF_TOKEN_INVALID";
    } catch (e) {
        return false;
    }
}

function handleSessionExpired() {
    if (window.sessionExpiredRedirecting) {
        return;
    }
    window.sessionExpiredRedirecting = true;
    unblockScreen();

    if (typeof infoDialog !== "undefined") {
        infoDialog.show("Sessione scaduta", "La sessione è scaduta. Effettua nuovamente l'accesso.", "Accedi", "goToUrl('" + contextPath + "/login')");
    } else {
        window.location.assign(contextPath + "/login");
    }
}

function handleCsrfInvalid() {
    if (window.csrfInvalidShown) {
        return;
    }
    window.csrfInvalidShown = true;
    unblockScreen();
    if (typeof infoDialog !== "undefined") {
        infoDialog.show("Richiesta non valida", "Per sicurezza la richiesta è stata rifiutata. Ricarica la pagina e riprova.", "OK", "refreshPage()");
    } else {
        window.location.reload();
    }
}

function showServiceUnavailableMessage(target) {
    var message = "Servizio temporaneamente non disponibile. Riprova tra qualche minuto.";
    var alertTarget = target || "#alert-container";

    if ($(alertTarget).length) {
        showAlert(message, "danger", alertTarget);
    } else {
        alert(message);
    }
}

function ajaxCall(url, payload, callback) {
    $.ajax({
        type: "POST",
        url: url,
        data: payload,
        success: function(data){
            if (data.common.ack == 'OK') {
                callback();
            } else {
                unblockScreen();
                showAlert("Errore: "+data.common.faultString, 'danger', '#alert-container');
            }
        },
        error: function(jqXHR){
            unblockScreen();
            if (!isSessionExpiredResponse(jqXHR) && !isCsrfInvalidResponse(jqXHR)) {
                showServiceUnavailableMessage('#alert-container');
            }
        }
    });
}

String.prototype.fmt = function (hash) {
    var string = this, key; for (key in hash) string = string.replace(new RegExp('\\{' + key + '\\}', 'gm'), hash[key]);
    return string;
};

function goToUrl(urlToGo) {
    window.location.href = urlToGo;
}

function postToUrl(path, params, method) {
    method = method || "post";

    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);
    addCsrfTokenToForm(form);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", (params[key] === null ? "" : params[key]));

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}

function submitExportExcel(path, params) {
    
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", path);
    addCsrfTokenToForm(form);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", (params[key] === null ? "" : params[key]));

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
    
}

/*
 * type: info, success, warning and danger
 */
function showAlert(message, type, target) {
    var alertComponent = createAlertComponent(message, type);
    $(alertComponent).hide().appendTo(target).fadeIn();
    if (type === "success") {
        scheduleSuccessAlertDismissal(alertComponent);
    }
    scrollToTop();
}

function showAlertWithoutScroll(message, type, target) {
    var alertComponent = createAlertComponent(message, type);
    $(alertComponent).hide().appendTo(target).fadeIn();
    if (type === "success") {
        scheduleSuccessAlertDismissal(alertComponent);
    }
}

function scheduleSuccessAlertDismissal(alertComponent) {
    window.setTimeout(function() {
        alertComponent.fadeTo(500, 0).slideUp(500, function() {
            $(this).alert("close");
        });
    }, 10000);
}

function setNextPageSuccessMessage(message) {
    sessionStorage.setItem("nextPageSuccessMessage", message);
}

function showNextPageSuccessMessage(target) {
    var message = sessionStorage.getItem("nextPageSuccessMessage");
    if (message) {
        sessionStorage.removeItem("nextPageSuccessMessage");
        showAlert(message, "success", target || "#alert-container");
    }
}

function createAlertComponent(message, type) {
    var alertComponent = $("<div>", {
        "class": "alert alert-dismissable alert-" + type,
        role: "alert"
    });
    var closeButton = $("<button>", {
        type: "button",
        "class": "close",
        "data-dismiss": "alert",
        "aria-label": "Chiudi"
    }).append($("<span>", {"aria-hidden": "true"}).text("×"));

    alertComponent.append(closeButton);
    alertComponent.append(document.createTextNode(message == null ? "" : String(message)));
    return alertComponent;
}

function scrollToTop() {
    $('html, body').animate({
      scrollTop: 0
    }, 1000, 'easeInOutExpo');
}

/*
 * iconClass: trash, edit, ecc...
 */
function getIconWithAction(action, iconClass, titleIcon) {
    var actionObj = $("<a href=\"javascript:void(0);\" onclick=\""+action+"\" title=\""+titleIcon+"\"><i class=\"fas fa-"+iconClass+"\"></i></a>");
    return actionObj.prop('outerHTML');    
}

/*
 * iconClass: trash, edit, ecc...
 */
function getIconWithConfirmAndAction(titleConfirm, bodyConfirm, action, iconClass, titleIcon) {
    var actionObj = $("<a href='javascript:void(0);' onclick='' title='"+titleIcon+"'><i class='fas fa-"+iconClass+"'></i></a>");
    var callback = "confirmDialog.show(\""+titleConfirm+"\", \""+bodyConfirm+"\", \""+action+"\")";
    actionObj.attr("onclick", callback);
    return actionObj.prop('outerHTML');    
}

/*
 * buttonClass: primary, secondary, ecc...
 */
function setConfirmAndActionOnButton(titleConfirm, bodyConfirm, action, buttonSelector) {
    var buttonObj = $(buttonSelector);
    var callback = "confirmDialog.show(\""+titleConfirm+"\", \""+bodyConfirm+"\", \""+action+"\")";
    buttonObj.attr("onclick", callback);
}

function refreshPage() {
    window.location.reload();
}

function blockScreen() {
    $(".loading").fadeIn();
}

function unblockScreen() {
    $(".loading").fadeOut();
}

function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) === ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) === 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function resetExportCompletedCookie() {
    var expires = "expires=Thu, 01 Jan 1970 00:00:00 GMT";
    var currentDirectory = window.location.pathname.substring(0, window.location.pathname.lastIndexOf("/"));
    document.cookie = "ExportCompleted=; " + expires + "; path=" + currentDirectory;
    document.cookie = "ExportCompleted=0; path=" + contextPath;
}
