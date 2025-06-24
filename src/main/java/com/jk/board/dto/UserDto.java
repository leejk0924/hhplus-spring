package com.jk.board.dto;

import com.jk.board.domain.User;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto(
        Long id,
        String username,
        String password,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static UserDto from(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    public static UserDto of(String username, String password) {
        return UserDto.builder()
                .username(username)
                .password(password)
                .build();
    }

    public User toEntity() {
        return User.of(
                username,
                password
        );
    }
}
