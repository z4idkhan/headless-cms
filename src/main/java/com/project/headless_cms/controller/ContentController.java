package com.project.headless_cms.controller;

import com.project.headless_cms.dto.ContentRequestDTO;
import com.project.headless_cms.dto.ContentResponseDTO;
import com.project.headless_cms.model.ArticleStatus;
import com.project.headless_cms.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @GetMapping
    public List<ContentResponseDTO> getAllContents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) ArticleStatus status
    ) {
        return contentService.getAllContents(keyword, status);
    }


    @GetMapping("/{id}")
    public ContentResponseDTO getContentById(
            @PathVariable Long id) {

        return contentService.getContentById(id);
    }


    @PostMapping
    public ContentResponseDTO createContent(
            @RequestBody ContentRequestDTO request) {

        return contentService.createContent(request);
    }


    @PutMapping("/{id}")
    public ContentResponseDTO updateContent(
            @PathVariable Long id,
            @RequestBody ContentRequestDTO request) {

        return contentService.updateContent(id, request);
    }


    @PutMapping("/{id}/publish")
    public ContentResponseDTO publishContent(
            @PathVariable Long id) {

        return contentService.publishContent(id);
    }


    @DeleteMapping("/{id}")
    public String deleteContent(
            @PathVariable Long id) {

        contentService.deleteContent(id);

        return "Content deleted successfully";
    }


    @GetMapping("/public")
    public List<ContentResponseDTO> getPublicContents() {

        return contentService.getPublicPublishedContents();
    }
}