package com.jk.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jk.board.domain.Article;
import com.jk.board.domain.User;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Builder
public record ArticleDto (
        Long id,
        String title,
        UserDto userDto,
        String content,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
        LocalDateTime modifiedAt
        ) {
    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                entity.getTitle(),
                UserDto.from(entity.getUserId()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
    public static List<ArticleDto> from(List<Article> articles) {
        return articles.stream()
                .map(ArticleDto::from)
                .sorted(Comparator.comparing(ArticleDto::createdAt).reversed())
                .toList();
    }
    public static ArticleDto of(UserDto userDto, String title, String content) {
        return ArticleDto.builder()
                .title(title)
                .userDto(userDto)
                .content(content)
                .build();
    }
    public static ArticleDto of (String title, String content) {
        return ArticleDto.builder()
                .title(title)
                .content(content)
                .build();
    }
    public Article toEntity(User user) {
        return Article.of(
                user,
                title,
                content
        );
    }
    public String getAuthor() {
        return userDto.username();
    }
}
