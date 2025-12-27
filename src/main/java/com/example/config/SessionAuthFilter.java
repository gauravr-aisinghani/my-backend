package com.example.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SessionAuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI();

        // ✅ FINAL RULE:
        // Payment APIs MUST work WITHOUT session
        if (path.startsWith("/api/payments")) {
            chain.doFilter(request, response);
            return;
        }

        // ✅ Existing public APIs
        if (
                path.startsWith("/api/auth") ||
                path.startsWith("/api/gdc") ||
                path.startsWith("/api/drivers")
        ) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        // ❌ NO SESSION → JUST CONTINUE (NO 401)
        if (session == null) {
            chain.doFilter(request, response);
            return;
        }

        Object role = session.getAttribute("ROLE");
        Object email = session.getAttribute("CLIENT_EMAIL");

        if (role == null || email == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        List.of(() -> role.toString())
                );

        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(request, response);
    }
}
