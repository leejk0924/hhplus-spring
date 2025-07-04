package com.jk.board.domain;

import com.jk.board.domain.type.Role;
import com.jk.board.domain.converter.RoleConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String username;
    @Column(length = 100, nullable = false)
    private String password;
    @Convert(converter = RoleConverter.class)
    @Column(length = 20, nullable = false)
    private Role role;

    private User(
            String username,
            String password,
            Role role
    ) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public static User of(
            String username,
            String password
    ) {
        return new User(username, password, Role.USER);
    }
    @PrePersist
    @PreUpdate
    public void applyNoopPrefix() {
        if (password != null && !password.startsWith("{noop}")) {
            this.password = "{noop}" + password;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if(!(object instanceof User that)) return false;
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
