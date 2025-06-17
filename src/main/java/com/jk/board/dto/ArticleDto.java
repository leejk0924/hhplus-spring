package com.jk.board.dto;

import com.jk.board.domain.Article;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

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
    public static List<ArticleDto> from(List<Article> articles) {
        return articles.stream()
                .map(ArticleDto::from)
                .sorted(Comparator.comparing(ArticleDto::createdAt).reversed())
                .toList();
    }
}
