package com.jk.board.controller;

import com.jk.board.dto.ArticleDto;
import com.jk.board.dto.response.ArticleResponse;
import com.jk.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> searchArticles() {
        List<ArticleDto> result = articleService.searchArticles();
        List<ArticleResponse> list = result.stream().map(ArticleResponse::from).toList();
        return ResponseEntity.ok(list);
    }
}
