package com.nttdata.idmccnobe.interceptor;

import com.nttdata.idmccnobe.util.Constants;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CsrfInterceptorTest {

    @Test
    public void acceptsUnsafeRequestWithTheSessionToken() throws Exception {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(Constants.SESSION_CSRF_TOKEN)).thenReturn("token-valido");
        when(request.getHeader(Constants.CSRF_HEADER_NAME)).thenReturn("token-valido");

        assertTrue(new CsrfInterceptor().preHandle(request, response, new Object()));
    }

    @Test
    public void rejectsUnsafeRequestWithoutTheSessionToken() throws Exception {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(Constants.SESSION_CSRF_TOKEN)).thenReturn("token-valido");
        when(response.getWriter()).thenReturn(new PrintWriter(new StringWriter()));

        assertFalse(new CsrfInterceptor().preHandle(request, response, new Object()));
    }
}
