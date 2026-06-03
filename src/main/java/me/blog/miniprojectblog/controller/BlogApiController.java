package me.blog.miniprojectblog.controller;

import lombok.RequiredArgsConstructor;
import me.blog.miniprojectblog.domain.Article;
import me.blog.miniprojectblog.dto.AddArticleRequest;
import me.blog.miniprojectblog.dto.ArticleResponse;
import me.blog.miniprojectblog.dto.UpdateArticleRequest;
import me.blog.miniprojectblog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Optional<Article> articleOptional = blogService.findById(id);

        if(articleOptional.isEmpty()) {
            throw new IllegalArgumentException("not found: " + id);
        }

        Article article = articleOptional.get();

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @PostMapping("/api/articles/delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/api/articles/update/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }
}
