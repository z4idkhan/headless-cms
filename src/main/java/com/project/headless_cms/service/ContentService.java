package com.project.headless_cms.service;

import com.project.headless_cms.model.*;
import com.project.headless_cms.repository.CategoryRepository;
import com.project.headless_cms.repository.ContentRepository;
import com.project.headless_cms.repository.TagRepository;
import com.project.headless_cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<Content> getAllContents(String keyword, ArticleStatus status) {
        if (keyword != null && !keyword.isBlank()) {
            return contentRepository.findByTitleContainingIgnoreCase(keyword);
        }

        if (status != null) {
            return contentRepository.findByStatus(status);
        }

        return contentRepository.findAll();
    }

    public Content createContent(Content content, Long authorId, Long categoryId, List<Long> tagIds) {
        Users author = userRepository.findById(authorId)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Tag> tags = tagRepository.findAllById(tagIds);

        content.setAuthor(author);
        content.setCategory(category);
        content.setTags(tags);
        content.setStatus(ArticleStatus.DRAFT);
        content.setViews(0);
        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());

        return contentRepository.save(content);
    }

    public Content updateContent(Long id, Content request, Long categoryId, List<Long> tagIds) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        List<Tag> tags = tagRepository.findAllById(tagIds);

        content.setTitle(request.getTitle());
        content.setBody(request.getBody());
        content.setCategory(category);
        content.setTags(tags);
        content.setUpdatedAt(LocalDateTime.now());

        return contentRepository.save(content);
    }

    public Content publishContent(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        content.setStatus(ArticleStatus.PUBLISHED);
        content.setUpdatedAt(LocalDateTime.now());

        return contentRepository.save(content);
    }

    public Content getContentById(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }

    public List<Content> getPublicPublishedContents() {
        return contentRepository.findByStatus(ArticleStatus.PUBLISHED);
    }
}