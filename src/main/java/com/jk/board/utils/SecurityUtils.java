package com.jk.board.utils;

import com.jk.board.dto.security.UserPrincipal;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

@Configuration
public class SecurityUtils {
    public static UserPrincipal getCurrentUser(Authentication authentication) {
        return (UserPrincipal) authentication.getPrincipal();
    }
}
