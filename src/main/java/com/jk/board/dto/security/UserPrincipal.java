package com.jk.board.dto.security;

import com.jk.board.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record UserPrincipal(
        String username,
        String password,
        Collection<GrantedAuthority> authorities
) implements UserDetails {
    public static UserPrincipal of(String username, String password, List<GrantedAuthority> authorities) {
        return new UserPrincipal(
                username,
                password,
                authorities
        );
    }
    public static UserPrincipal from(UserDto dto) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(dto.role());
        return UserPrincipal.of(
                dto.username(),
                dto.password(),
                List.of(authority)
        );
    }
    public String getRole() {
        return getAuthorities().stream().findFirst().map(GrantedAuthority::getAuthority).orElse(null);
    }
    // 권한 : 현재는 권한은 없음, 추후 추가 예정
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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
}
