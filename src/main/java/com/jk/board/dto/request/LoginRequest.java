package com.jk.board.dto.request;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.jk.board.dto.user.LoginDto;
import jakarta.validation.Valid;

public record LoginRequest(
        @JsonUnwrapped @Valid UserRequest userRequest
        ) {
    public LoginDto of () {
        return new LoginDto(userRequest.username(), userRequest.password());
    }
}
