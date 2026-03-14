package com.project.headless_cms.controller;

import com.project.headless_cms.model.ArticleStatus;
import com.project.headless_cms.model.Content;
import com.project.headless_cms.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping
    public List<Content> getAllContents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) ArticleStatus status
    ) {
        return contentService.getAllContents(keyword, status);
    }

    @GetMapping("/{id}")
    public Content getContentById(@PathVariable Long id) {
        return contentService.getContentById(id);
    }

    @PostMapping
    public Content createContent(
            @RequestBody Content content,
            @RequestParam Long authorId,
            @RequestParam Long categoryId,
            @RequestParam List<Long> tagIds
    ) {
        return contentService.createContent(content, authorId, categoryId, tagIds);
    }

    @PutMapping("/{id}")
    public Content updateContent(
            @PathVariable Long id,
            @RequestBody Content content,
            @RequestParam Long categoryId,
            @RequestParam List<Long> tagIds
    ) {
        return contentService.updateContent(id, content, categoryId, tagIds);
    }

    @PutMapping("/{id}/publish")
    public Content publishContent(@PathVariable Long id) {
        return contentService.publishContent(id);
    }

    @DeleteMapping("/{id}")
    public String deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return "Content deleted successfully";
    }

    @GetMapping("/public")
    public List<Content> getPublicContents() {
        return contentService.getPublicPublishedContents();
    }
}