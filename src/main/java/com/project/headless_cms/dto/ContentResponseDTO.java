package com.project.headless_cms.dto;

import com.project.headless_cms.model.ArticleStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ContentResponseDTO {

    private Long id;
    private String title;
    private String body;
    private ArticleStatus status;
    private Integer views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long authorId;
    private Long categoryId;
    private List<Long> tagIds;

    public ContentResponseDTO(
            Long id,
            String title,
            String body,
            ArticleStatus status,
            Integer views,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long authorId,
            Long categoryId,
            List<Long> tagIds
    ) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.status = status;
        this.views = views;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.tagIds = tagIds;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public Integer getViews() {
        return views;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }
}