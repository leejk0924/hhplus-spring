package com.jk.board.dto.security;

import com.jk.board.dto.UserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record UserPrincipal(
        String username,
        String password
) implements UserDetails {
    public static UserPrincipal of(String username, String password) {
        return new UserPrincipal(
                username,
                password
        );
    }

    public static UserPrincipal from(UserDto dto) {
        return UserPrincipal.of(
                dto.username()
                , dto.password()
        );
    }
    // 권한 : 현재는 권한은 없음, 추후 추가 예정
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(RoleType.USER.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum RoleType {
        USER("ROLE_USER");
        @Getter
        private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }
}
