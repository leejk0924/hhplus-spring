package com.jk.board.dto.request;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.jk.board.dto.user.RegisterDto;
import jakarta.validation.Valid;

public record RegisterRequest (
        @JsonUnwrapped @Valid UserRequest userRequest
) {
    public RegisterDto of() {
        return new RegisterDto(
                userRequest.username(),
                userRequest.password()
        );
    }
}
