package com.jk.board.service;

import com.jk.board.dto.ArticleDto;
import com.jk.board.dto.security.UserPrincipal;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ArticlePermission {
    public boolean canEdit(ArticleDto articleDto, UserPrincipal userPrincipal) {
        String author = articleDto.getAuthor();
        String user = userPrincipal.getUsername();
        return Objects.equals(author, user);
    }
}
