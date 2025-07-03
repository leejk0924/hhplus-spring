package com.jk.board.dto.security;

import com.jk.board.service.TokenService;
import com.jk.board.utils.JwtHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final JwtHelper jwtHelper;
    private final List<String> EXCLUDE_PATH = List.of("/login", "/register");
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            var token = jwtHelper.extractToken(request);
            if (jwtHelper.validationToken(token) || tokenService.isValidToken(token)) {
                Authentication auth = jwtHelper.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e ){
            sendUnauthorizedResponse(response, e);
        }
    }
    private static void sendUnauthorizedResponse(HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        response.getWriter().flush();
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return EXCLUDE_PATH.contains(request.getServletPath());
    }
}
