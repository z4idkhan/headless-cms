package com.project.headless_cms.service;

import com.project.headless_cms.dto.ContentRequestDTO;
import com.project.headless_cms.dto.ContentResponseDTO;
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


    // GET ALL CONTENTS

    public List<ContentResponseDTO> getAllContents(
            String keyword,
            ArticleStatus status) {

        List<Content> contents;

        if (keyword != null && !keyword.isBlank()) {

            contents = contentRepository
                    .findByTitleContainingIgnoreCase(keyword);

        } else if (status != null) {

            contents = contentRepository.findByStatus(status);

        } else {

            contents = contentRepository.findAll();
        }

        return contents.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }


    // CREATE CONTENT

    public ContentResponseDTO createContent(
            ContentRequestDTO request) {

        Users author = userRepository.findById(request.getAuthorId())
                .orElseThrow(() ->
                        new RuntimeException("Author not found"));


        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));


        List<Tag> tags = tagRepository
                .findAllById(request.getTagIds());


        Content content = new Content();

        content.setTitle(request.getTitle());
        content.setBody(request.getBody());

        content.setAuthor(author);
        content.setCategory(category);
        content.setTags(tags);

        content.setStatus(ArticleStatus.DRAFT);
        content.setViews(0);

        content.setCreatedAt(LocalDateTime.now());
        content.setUpdatedAt(LocalDateTime.now());


        Content savedContent =
                contentRepository.save(content);

        return convertToResponseDTO(savedContent);
    }


    // UPDATE CONTENT

    public ContentResponseDTO updateContent(
            Long id,
            ContentRequestDTO request) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Content not found"));


        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));


        List<Tag> tags = tagRepository
                .findAllById(request.getTagIds());


        content.setTitle(request.getTitle());
        content.setBody(request.getBody());

        content.setCategory(category);
        content.setTags(tags);

        content.setUpdatedAt(LocalDateTime.now());


        Content updatedContent =
                contentRepository.save(content);

        return convertToResponseDTO(updatedContent);
    }


    // PUBLISH CONTENT

    public ContentResponseDTO publishContent(Long id) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Content not found"));


        content.setStatus(ArticleStatus.PUBLISHED);
        content.setUpdatedAt(LocalDateTime.now());


        Content publishedContent =
                contentRepository.save(content);

        return convertToResponseDTO(publishedContent);
    }


    // GET CONTENT BY ID

    public ContentResponseDTO getContentById(Long id) {

        Content content = contentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Content not found"));

        return convertToResponseDTO(content);
    }


    // DELETE CONTENT

    public void deleteContent(Long id) {

        contentRepository.deleteById(id);
    }


    // GET PUBLIC CONTENT

    public List<ContentResponseDTO> getPublicPublishedContents() {

        List<Content> contents =
                contentRepository.findByStatus(
                        ArticleStatus.PUBLISHED);

        return contents.stream()
                .map(this::convertToResponseDTO)
                .toList();
    }


    // ENTITY → RESPONSE DTO

    private ContentResponseDTO convertToResponseDTO(
            Content content) {

        List<Long> tagIds = content.getTags()
                .stream()
                .map(Tag::getId)
                .toList();


        List<String> tagNames = content.getTags()
                .stream()
                .map(Tag::getName)
                .toList();


        return new ContentResponseDTO(

                content.getId(),

                content.getTitle(),

                content.getBody(),

                content.getStatus(),

                content.getViews(),

                content.getCreatedAt(),

                content.getUpdatedAt(),

                content.getAuthor().getId(),

                content.getAuthor().getUsername(),

                content.getCategory().getId(),

                content.getCategory().getName(),

                tagIds,

                tagNames
        );
    }
}