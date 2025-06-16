package com.jk.board.service;

import com.jk.board.domain.Article;
import com.jk.board.domain.User;
import com.jk.board.dto.ArticleDto;
import com.jk.board.repository.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles
@DisplayName("게시글 - 서비스")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 검색")
    @Test
    void givenNothing_whenRequestArticles_thenReturnsArticles() {
        // given
        User testUser = User.of("test1", "password1");
        List<Article> articles = List.of(
                Article.of(testUser, "title1", "content1"),
                Article.of(testUser, "title2", "content2"));

        Mockito.when(articleRepository.findAll())
                .thenReturn(articles);

        List<ArticleDto> result = sut.searchArticles();
        assertThat(result).hasSize(2);
        assertThat(result.get(0).title()).isEqualTo("title1");
        assertThat(result.get(0).content()).isEqualTo("content1");
        assertThat(result.get(0).author()).isEqualTo("test1");
    }
}