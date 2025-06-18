package com.jk.board.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jk.board.dto.ArticleDto;

import java.time.LocalDateTime;

public record ArticleResponse (
        Long id,
        String title,
        String username,
        String content,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime modifiedAt
) {
    public static ArticleResponse from(ArticleDto dto) {
        return new ArticleResponse(
                dto.id(),
                dto.title(),
                dto.userDto().username(),
                dto.content(),
                dto.createdAt(),
                dto.modifiedAt() == null ? dto.createdAt() : dto.modifiedAt()
        );
    }
}
