package com.jk.board.domain;

import com.jk.board.dto.ArticleDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedQuery(
        name = "Article.findByIdWithUser",
        query = "SELECT a FROM Article a JOIN FETCH a.userId WHERE a.id= :id"
)
public class Article extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
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

    public void updateFromDto(ArticleDto articleDto) {
        if (articleDto.title() != null) {
            this.title = articleDto.title();
        }
        if (articleDto.content() != null) {
            this.content = articleDto.content();
        }
    }
}
