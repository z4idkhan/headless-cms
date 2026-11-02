package com.project.headless_cms.repository;

import com.project.headless_cms.model.ArticleStatus;
import com.project.headless_cms.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByStatus(ArticleStatus status);
    List<Content> findByTitleContainingIgnoreCase(String keyword);
    long countByStatus(ArticleStatus status);
}