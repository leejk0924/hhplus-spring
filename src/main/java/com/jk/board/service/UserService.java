package com.jk.board.service;

import com.jk.board.dto.security.UserPrincipal;
import com.jk.board.dto.user.LoginDto;
import com.jk.board.dto.user.RegisterDto;
import com.jk.board.exception.UserAlreadyExistsException;
import com.jk.board.repository.UserRepository;
import com.jk.board.utils.JwtUtils;
import com.jk.board.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authManager;
    private final JwtUtils jwtTokenProvider;
    public void registerUser(RegisterDto request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("이미 존재하는 username 입니다.");
        }
        userRepository.save(request.toEntity());
    }
    public String login(LoginDto loginDto) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        Authentication authenticate = authManager.authenticate(authToken);
        UserPrincipal user = SecurityUtils.getCurrentUser(authenticate);
        return jwtTokenProvider.generateToken(user.username());
    }
}
