package com.jk.board.utils;

import com.jk.board.dto.security.UserPrincipal;
import com.jk.board.exception.ArticlePermissionException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtHelper {
    private static final String TOKEN_PREFIX = "Bearer ";
    private final long expired;
    private final SecretKey secretKey;
    private final JwtParser parser;
    public JwtHelper(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expired-time}") long expired) {
        this.expired = expired;
        this.secretKey =  Keys.hmacShaKeyFor(secret.getBytes());
        this.parser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build();
    }

    public String generateToken(UserPrincipal userDetails) {
        var now = new Date();
        Date expiredDate = new Date(now.getTime() + expired);
        return TOKEN_PREFIX + Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role", userDetails.getRole())
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
    public boolean validationToken(String token) {
        try {
            parser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new JwtException("토큰이 유효하지 않습니다: " + e.getMessage(), e);
        }
    }
    public String extractToken(HttpServletRequest request) {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearer != null && bearer.startsWith(TOKEN_PREFIX)) {
            return bearer.substring(TOKEN_PREFIX.length());
        }
        throw new ArticlePermissionException("유효하지 않는 토큰입니다.");
    }
    public Authentication getAuthentication(String token) {
        Claims claims = parser.parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        String role = claims.get("role", String.class);

        UserPrincipal userPrincipal = UserPrincipal.of(username, null, List.of(new SimpleGrantedAuthority(role)));
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(claims.get("role", String.class)));
        return new UsernamePasswordAuthenticationToken(userPrincipal, "", authorities);
    }
}
