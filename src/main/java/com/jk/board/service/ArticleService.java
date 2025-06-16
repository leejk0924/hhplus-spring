package com.jk.board.service;

import com.jk.board.domain.Article;
import com.jk.board.dto.ArticleDto;
import com.jk.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public List<ArticleDto> searchArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().map(ArticleDto::from).toList();
    }
}
