package com.jk.board.controller;

import com.jk.board.dto.ArticleDto;
import com.jk.board.dto.request.ArticleRequest;
import com.jk.board.dto.response.ArticleResponse;
import com.jk.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody ArticleRequest articleRequest) {
        ArticleDto dto = articleService.saveArticle(articleRequest.toDto());
        return ResponseEntity.ok(ArticleResponse.from(dto));
    }
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable(name = "id") Long articleId) {
        ArticleDto article = articleService.getArticle(articleId);
        return ResponseEntity.ok(ArticleResponse.from(article));
    }
    @PutMapping("/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable(name = "id") Long articleId,
            @RequestBody ArticleRequest articleRequest) {
        ArticleDto articleDto = articleService.updateArticle(articleId, articleRequest.toDto());
        return ResponseEntity.ok(ArticleResponse.from(articleDto));
    }
}
