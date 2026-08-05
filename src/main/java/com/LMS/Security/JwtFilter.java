package com.LMS.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

@Autowired
private JwtUtil jwtUtil;

@Autowired
private CustomUserDetailsService userDetailsService;

@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    String path = request.getServletPath();

    //  PUBLIC URLS
    if (path.startsWith("/auth") || path.startsWith("/uploads")) {
        filterChain.doFilter(request, response);
        return;
    }

    String authHeader = request.getHeader("Authorization");
    String token = null;
    String email = null;

    try {

        //  DEBUG
        System.out.println("🔐 AUTH HEADER: " + authHeader);

        //  Extract Token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7);

            email = jwtUtil.extractEmail(token);

            System.out.println(" TOKEN: " + token);
            System.out.println(" EMAIL: " + email);
        }

        //  Validate Token
        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(email);

            if (jwtUtil.validateToken(token, userDetails.getUsername())) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);

                System.out.println(" AUTHENTICATED: "
                        + userDetails.getUsername());

                System.out.println(" ROLES: "
                        + userDetails.getAuthorities());

                System.out.println(" USER: "
                        + SecurityContextHolder
                        .getContext()
                        .getAuthentication());

            } else {

                System.out.println(" TOKEN INVALID");
            }
        }

    } catch (Exception e) {

        System.out.println(" JWT ERROR: " + e.getMessage());
    }

    //  Continue Filter Chain
    filterChain.doFilter(request, response);
}


}
