package com.jk.board.dto;

import com.jk.board.domain.User;
import com.jk.board.dto.request.UserRequest;

public record UserRegisterDto(
        String username,
        String password
) {
    public static UserRegisterDto of(UserRequest request) {
        return new UserRegisterDto(
                request.username(),
                request.password()
        );
    }
    public User toEntity () {
        return User.of(username, password);
    }
}
