package com.jk.board.dto.user;

import com.jk.board.domain.User;

public record RegisterDto (
        String username,
        String password
) {
    public User toEntity() {
        return User.of(username, password);
    }
}
