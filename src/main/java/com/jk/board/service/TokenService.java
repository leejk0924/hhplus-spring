package com.jk.board.service;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${jwt.expired-time}")
    private Long expirationDuration;
    public void storeAccessToken(String token) {
        redisTemplate.opsForValue().set(token, "access", Duration.ofMillis(expirationDuration));
    }
    public boolean isValidToken(String token) {
        if (redisTemplate.hasKey(token)) {
            return redisTemplate.hasKey(token);
        } else {
            throw new JwtException("유효하지 않은 토큰입니다.");
        }
    }
}
