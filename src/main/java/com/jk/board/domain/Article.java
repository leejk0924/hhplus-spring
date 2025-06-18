package com.jk.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_Id")
    private User userId;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 500)
    private String content;

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
