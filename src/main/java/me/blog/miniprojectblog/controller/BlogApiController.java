package me.blog.miniprojectblog.controller;

import lombok.RequiredArgsConstructor;
import me.blog.miniprojectblog.domain.Article;
import me.blog.miniprojectblog.dto.AddArticleRequest;
import me.blog.miniprojectblog.dto.ArticleResponse;
import me.blog.miniprojectblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
//        List<ArticleResponse> articles = blogService.findAll()
//                .stream()
//                .map(ArticleResponse::new)
//                .toList();

        List<ArticleResponse> outArticleResponse = new ArrayList<>();
        List<Article> outArticle = blogService.findAll();

        for (Article article : outArticle) {
            outArticleResponse.add(new ArticleResponse(article));
        }

        return ResponseEntity.ok()
                .body(outArticleResponse);
    }

}
