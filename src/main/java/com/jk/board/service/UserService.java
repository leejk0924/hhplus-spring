package com.jk.board.service;

import com.jk.board.dto.UserRegisterDto;
import com.jk.board.exception.UserAlreadyExistsException;
import com.jk.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void registerUser(UserRegisterDto request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserAlreadyExistsException("이미 존재하는 username 입니다.");
        }
        userRepository.save(request.toEntity());
    }
}
