package org.onlinebookstore.onlinebookstorebackend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();

        // Allow anonymous access to specific paths
        if (requestURI.equals("/login") || requestURI.equals("/register")) {
            return true;
        }

        if (session != null && session.getAttribute("userId") != null) {
            return true;
        }

        response.setStatus(401);
        response.sendRedirect("/login");
        System.out.println("Unauthorized access to " + requestURI);
        return false;
    }
}
