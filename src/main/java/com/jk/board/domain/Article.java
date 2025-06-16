package com.jk.board.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private User userId;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 500)
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @CreatedDate
    private LocalDateTime createdAt;

    protected Article() {}

    private Article(User userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }

    public static Article of(User user, String title, String content) {
        return new Article(user, title, content);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if(!(object instanceof Article that)) return false;
        return this.getId() != null && this.getId().equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
