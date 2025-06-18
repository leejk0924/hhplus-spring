package com.jk.board.service;

import com.jk.board.domain.Article;
import com.jk.board.domain.User;
import com.jk.board.dto.ArticleDto;
import com.jk.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ActiveProfiles
@DisplayName("게시글 - 서비스")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("게시글 전체 조회")
    void givenNothing_whenRequestArticles_thenReturnsArticles() throws Exception{
        // given
        User testUser = User.of("test1", "password1");
        Article article1 = Article.of(testUser, "title1", "content1");
        ReflectionTestUtils.setField(article1, "createdAt", LocalDateTime.now());

        Article article2 = Article.of(testUser, "title2", "content2");
        ReflectionTestUtils.setField(article2, "createdAt", LocalDateTime.now());
        List<Article> articles = List.of(article1, article2);

        when(articleRepository.findAll()).thenReturn(articles);

        // when
        List<ArticleDto> result = sut.searchArticles();

        // then
        assertThat(result).hasSize(2);
        assertThat(result.get(0).title()).isEqualTo("title2");
        assertThat(result.get(0).content()).isEqualTo("content2");
        assertThat(result.get(0).userDto().username()).isEqualTo("test1");
    }
}