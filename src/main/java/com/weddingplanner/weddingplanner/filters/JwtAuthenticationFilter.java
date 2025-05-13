package com.weddingplanner.weddingplanner.filters;

import com.weddingplanner.weddingplanner.services.JwtService;
import com.weddingplanner.weddingplanner.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;


    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);

        if(!jwtService.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtService.extractUsername(token);
        String rolesString = jwtService.extractRoles(token);

        Collection<GrantedAuthority> authorities = extractAuthorities(rolesString);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    private Collection<GrantedAuthority> extractAuthorities(String roleString) {
        if (roleString == null || roleString.isEmpty()) {
            return Collections.emptyList();
        }
        String formattedRole = roleString.startsWith("ROLE_") ? roleString : "ROLE_" + roleString.toUpperCase();
        return List.of(new SimpleGrantedAuthority(formattedRole));
    }

}
