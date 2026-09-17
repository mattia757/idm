package com.nttdata.idmccnobe.interceptor;

import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.util.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 *
 * @author DelorenziVa
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        UserProfile userProfile = session != null ? (UserProfile) session.getAttribute(Constants.SESSION_USER_PROFILE) : null;
        if (userProfile != null && userProfile.getUsername()!=null && userProfile.getIdCooperativa()!=null && userProfile.getRole()!=null && userProfile.getNomeCooperativa()!=null) {
            request.setAttribute(Constants.USER_PROFILE, userProfile);
        } else {
            String requestPath = request.getRequestURI().substring(request.getContextPath().length());
            if (requestPath.startsWith("/rest/")) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.getWriter().write("{\"error\":\"SESSION_EXPIRED\"}");
                return false;
            }
            response.sendRedirect("/IdmCCNOBackend/login");
            return false;
        }
        return true;

    }
}
