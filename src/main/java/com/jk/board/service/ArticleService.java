package com.jk.board.service;

import com.jk.board.domain.Article;
import com.jk.board.domain.User;
import com.jk.board.dto.ArticleDto;
import com.jk.board.dto.UserDto;
import com.jk.board.exception.ArticlePermissionException;
import com.jk.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleDto> searchArticles() {
        return ArticleDto.from(articleRepository.findAll());
    }

    @Transactional
    public ArticleDto saveArticle(ArticleDto articleDto) {
        var article = convertToEntity(articleDto);
        var savedArticle = articleRepository.save(article);
        return ArticleDto.from(savedArticle);
    }

    @Transactional(readOnly = true)
    public ArticleDto getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. ID : " + id));
        return ArticleDto.from(article);
    }
    @Transactional
    public ArticleDto updateArticle(Long articleId, ArticleDto articleDto) {
        Article article = articleRepository.findByIdWithUser(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. ID : " + articleId));

        if(!articleDto.userDto().matches(article.getUserId())) {
            throw new ArticlePermissionException("username과 password를 다시 확인해 주세요");
        }

        article.updateFromDto(articleDto);
        return ArticleDto.from(article);
    }
    private static Article convertToEntity(ArticleDto articleDto) {
        return articleDto.toEntity(articleDto.userDto().toEntity());
    }
}
