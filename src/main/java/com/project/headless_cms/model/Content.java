package com.project.headless_cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Content {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(length=5000)
    private String body;

    private boolean published;

}