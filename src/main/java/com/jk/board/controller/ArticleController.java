package com.jk.board.controller;

import com.jk.board.dto.ArticleDto;
import com.jk.board.dto.request.ArticleRequest;
import com.jk.board.dto.request.PasswordRequest;
import com.jk.board.dto.response.ArticleResponse;
import com.jk.board.dto.security.UserPrincipal;
import com.jk.board.service.ArticleService;
import com.jk.board.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final TokenService tokenService;

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponse>> searchArticles() {
        List<ArticleDto> result = articleService.searchArticles();
        List<ArticleResponse> list = result.stream().map(ArticleResponse::from).toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/articles")
    public ResponseEntity<ArticleResponse> saveArticle(@RequestBody @Valid ArticleRequest articleRequest) {
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
            @RequestBody @Valid ArticleRequest articleRequest,
            @AuthenticationPrincipal UserPrincipal userPrincipal) {
        ArticleDto articleDto = articleService.updateArticle(articleId, articleRequest.toDto(), userPrincipal);
        return ResponseEntity.ok(ArticleResponse.from(articleDto));
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Map<String, String>> deleteArticle(
            @PathVariable(name = "id") Long articleId,
            @RequestBody PasswordRequest request) {
        articleService.deleteArticle(articleId, request.password());

        return ResponseEntity.ok(Map.of("message", "Deleted Article"));
    }
}
