package com.jk.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final RedisTemplate<String, String> redisTemplate;
    public void storeAccessToken(String token, Duration time) {
        redisTemplate.opsForValue().set(token, "access", time);
    }
}
