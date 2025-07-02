package com.jk.board.dto.request;

import com.jk.board.dto.ArticleDto;
import com.jk.board.dto.UserDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ArticleRequest (
        @NotBlank(message = "title 을 입력해주세요.")
        String title,
        String content
) {
    public ArticleDto toDto() {
        return ArticleDto.of(
                title,
                content
        );
    }
}
