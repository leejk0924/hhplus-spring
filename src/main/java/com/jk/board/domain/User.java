package com.jk.board.domain;

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

    private User(
            String username,
            String password
    ) {
        this.username = username;
        this.password = password;
    }
    public static User of(
            String username,
            String password
    ) {
        return new User(username, password);
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
