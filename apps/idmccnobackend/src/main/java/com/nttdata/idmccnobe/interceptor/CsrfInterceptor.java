package com.nttdata.idmccnobe.interceptor;

import com.nttdata.idmccnobe.util.Constants;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

/** Validates a per-session CSRF token for every unsafe authenticated request. */
public class CsrfInterceptor implements HandlerInterceptor {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!isUnsafeMethod(request.getMethod())) {
            return true;
        }

        HttpSession session = request.getSession(false);
        String expectedToken = session != null ? (String) session.getAttribute(Constants.SESSION_CSRF_TOKEN) : null;
        String receivedToken = request.getHeader(Constants.CSRF_HEADER_NAME);
        if (receivedToken == null) {
            receivedToken = request.getParameter(Constants.CSRF_PARAMETER_NAME);
        }

        if (expectedToken != null && receivedToken != null
                && MessageDigest.isEqual(expectedToken.getBytes(StandardCharsets.UTF_8), receivedToken.getBytes(StandardCharsets.UTF_8))) {
            return true;
        }

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write("{\"error\":\"CSRF_TOKEN_INVALID\"}");
        return false;
    }

    public static String createToken() {
        byte[] token = new byte[32];
        SECURE_RANDOM.nextBytes(token);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(token);
    }

    private boolean isUnsafeMethod(String method) {
        return "POST".equalsIgnoreCase(method)
                || "PUT".equalsIgnoreCase(method)
                || "PATCH".equalsIgnoreCase(method)
                || "DELETE".equalsIgnoreCase(method);
    }
}
