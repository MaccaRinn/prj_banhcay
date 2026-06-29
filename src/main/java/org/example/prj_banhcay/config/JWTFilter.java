package org.example.prj_banhcay.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.prj_banhcay.service.JWTService;
import org.example.prj_banhcay.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter; // <-- Thêm dòng import này
import org.springframework.stereotype.Component; // <-- Thêm để Spring Boot quản lý Bean này

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter { // <-- Bắt buộc phải EXTENDS ở đây

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // if token is valid configure Spring Security to manually set authentication
            UserDetails userDetails = (UserDetails) context.getBean(MyUserDetailService.class).loadUserByUsername(username);

            if(jwtService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}