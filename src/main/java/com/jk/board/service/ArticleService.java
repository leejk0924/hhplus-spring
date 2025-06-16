package com.jk.board.service;

import com.jk.board.dto.ArticleDto;
import com.jk.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public List<ArticleDto> searchArticles() {
        return ArticleDto.from(articleRepository.findAll());
    }
}
