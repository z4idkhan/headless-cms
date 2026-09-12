package com.project.headless_cms.dto;

public class CategoryResponseDTO {

    private Long id;
    private String name;
    private String slug;
    private String description;

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(Long id, String name, String slug, String description) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }
}