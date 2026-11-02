package com.project.headless_cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String slug;

    private String description;

    // ✅ NEW: who created this category (null = default/seeded)
    @ManyToOne
    @JoinColumn(name = "created_by")
    @JsonIgnore // never send the user object (has password hash)
    private Users createdBy;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.slug = name == null ? null : name.toLowerCase().replace(" ", "-");
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    // ✅ Sent to frontend instead of the full user
    public String getCreatedByEmail() {
        return createdBy == null ? null : createdBy.getEmail();
    }
}