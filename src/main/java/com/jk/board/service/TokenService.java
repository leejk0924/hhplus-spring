package com.jk.board.service;

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
    private Duration expirationDuration;
    public void storeAccessToken(String token) {
        redisTemplate.opsForValue().set(token, "access", expirationDuration);
    }
}
