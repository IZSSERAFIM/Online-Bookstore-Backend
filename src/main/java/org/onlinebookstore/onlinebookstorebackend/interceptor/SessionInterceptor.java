package org.onlinebookstore.onlinebookstorebackend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();

        System.out.println(session.getAttribute("userId"));
        // Allow anonymous access to specific paths
        if (requestURI.equals("/login") || requestURI.equals("/register")) {
            return true;
        }

        if (session != null && session.getAttribute("userId") != null) {
            return true;
        }

        response.setStatus(401);
        return false;
    }
}
