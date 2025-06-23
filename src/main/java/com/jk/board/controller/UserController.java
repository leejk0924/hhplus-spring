package com.jk.board.controller;

import com.jk.board.dto.request.UserRequest;
import com.jk.board.service.TokenService;
import com.jk.board.service.UserService;
import com.jk.board.utils.JwtUtils;
import com.jk.board.utils.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtTokenProvider;
    private final TokenService tokenService;
    private final UserService userService;
    @Value("${jwt.expired-time}")
    private Duration expirationDuration;
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid UserRequest request) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        Authentication authenticate = authenticationManager.authenticate(authToken);
        var userDetail = SecurityUtils.getCurrentUser(authenticate);

        var jwt = jwtTokenProvider.generateToken(userDetail.username());
        tokenService.storeAccessToken(jwt, expirationDuration);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .body(Map.of("message", "로그인에 성공하였습니다."));
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid UserRequest request) {
        userService.registerUser(request.of());
        return ResponseEntity.ok().body(Map.of("message", "회원가입에 성공하였습니다."));
    }
}
