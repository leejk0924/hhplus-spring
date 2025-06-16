package com.jk.board.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
public class User {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20)
    private String username;
    @Column(length = 100, nullable = false)
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @CreatedDate
    private LocalDateTime createdAt;

    private User(
            String username,
            String password
    ) {
        this.username = username;
        this.password = password;
    }
    protected User(){}
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
