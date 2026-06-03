package me.blog.miniprojectblog.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.blog.miniprojectblog.domain.Article;
import me.blog.miniprojectblog.dto.AddArticleRequest;
import me.blog.miniprojectblog.dto.UpdateArticleRequest;
import me.blog.miniprojectblog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

//    public Article findById(long id) {
//        return blogRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
//    }

    public Optional<Article> findById(long id) {
        return blogRepository.findById(id);
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
