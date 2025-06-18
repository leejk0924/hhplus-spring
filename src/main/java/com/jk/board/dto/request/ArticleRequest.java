package com.jk.board.dto.request;

import com.jk.board.dto.ArticleDto;
import com.jk.board.dto.UserDto;

public record ArticleRequest (
        String title,
        String username,
        String password,
        String content
) {
    public ArticleDto toDto() {
        return ArticleDto.of(
                UserDto.of(
                        username,
                        password
                ),
                title,
                content
        );
    }
}
