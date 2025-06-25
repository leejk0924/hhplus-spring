package com.jk.board.controller;

import com.jk.board.dto.request.LoginRequest;
import com.jk.board.dto.request.RegisterRequest;
import com.jk.board.service.TokenService;
import com.jk.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final TokenService tokenService;
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest request) {
        var jwt = userService.login(request.of());
        tokenService.storeAccessToken(jwt);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwt)
                .body(Map.of("message", "로그인에 성공하였습니다."));
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid RegisterRequest request) {
        userService.registerUser(request.of());
        return ResponseEntity.ok().body(Map.of("message", "회원가입에 성공하였습니다."));
    }
}
