package com.jk.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

record UserRequest (
        @NotBlank(message = "username 을 입력해주세요.")
        @Size(min = 4, max = 10, message = "username 은 4자 이상 10자 이하입니다.")
        @Pattern(regexp = "^[a-z0-9]+$", message = "username 은 소문자 알파벳과 숫자만 사용할 수 있습니다.")
        String username,
        @NotBlank(message = "password 을 입력해주세요.")
        @Size(min = 8, max = 15,  message = "password 는 8자 이상 15자 이하입니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "password 는 영문 대소문자와 숫자만 사용할 수 있습니다.")
        String password
) {
}
