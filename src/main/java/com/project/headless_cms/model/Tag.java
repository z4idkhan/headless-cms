package com.project.headless_cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String slug;

    // ✅ NEW: who created this tag (null = default/seeded)
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