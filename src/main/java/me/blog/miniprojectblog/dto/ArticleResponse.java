package me.blog.miniprojectblog.dto;

import lombok.Getter;
import me.blog.miniprojectblog.domain.Article;

import java.util.Optional;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
