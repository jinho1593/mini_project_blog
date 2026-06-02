package me.blog.miniprojectblog.service;

import lombok.RequiredArgsConstructor;
import me.blog.miniprojectblog.domain.Article;
import me.blog.miniprojectblog.dto.AddArticleRequest;
import me.blog.miniprojectblog.repository.BlogRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }
}
