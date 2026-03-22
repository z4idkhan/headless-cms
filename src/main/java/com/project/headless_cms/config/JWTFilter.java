package com.project.headless_cms.config;

import com.project.headless_cms.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JWTFilter(JWTUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // ✅ ALWAYS get path from request (CORRECT WAY)
        String path = request.getServletPath();

        // ✅ SKIP AUTH ENDPOINTS (VERY IMPORTANT)
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        System.out.println("🔥 JWT FILTER EXECUTED");

        String authHeader = request.getHeader("Authorization");
        System.out.println("Auth Header: " + authHeader);

        String token = null;
        String username = null;

        // ✅ Extract token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

            try {
                username = jwtUtil.extractUserName(token);
                System.out.println("Username from token: " + username);
                System.out.println("Role from token: " + jwtUtil.extractRole(token));
            } catch (Exception e) {
                System.out.println("❌ Token parsing failed");
                filterChain.doFilter(request, response);
                return;
            }
        }

        // ✅ Authenticate user
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("Authorities: " + userDetails.getAuthorities());

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("✅ Authentication set");
            } else {
                System.out.println("❌ Token validation failed");
            }
        }

        filterChain.doFilter(request, response);
    }
}