package com.jk.board.service;

import com.jk.board.domain.Article;
import com.jk.board.dto.ArticleDto;
import com.jk.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private static Article convertToEntity(ArticleDto articleDto) {
        return articleDto.toEntity(articleDto.userDto().toEntity());
    }
}
