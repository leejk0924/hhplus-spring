package com.jk.board.dto;

import com.jk.board.domain.Article;

import java.time.LocalDateTime;

public record ArticleDto (
        Long id,
        String title,
        String author,
        String content,
        LocalDateTime createdAt) {
    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                entity.getTitle(),
                entity.getUserId().getUsername(),
                entity.getContent(),
                entity.getCreatedAt()
        );
    }
}
