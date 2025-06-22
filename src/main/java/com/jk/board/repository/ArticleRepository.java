package com.jk.board.repository;

import com.jk.board.domain.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @NonNull
    @EntityGraph(attributePaths = {"userId"})
    List<Article> findAll();
    Optional<Article> findByIdWithUser(Long id);
}

