package com.project.headless_cms.service;

import com.project.headless_cms.model.ArticleStatus;
import com.project.headless_cms.model.Content;
import com.project.headless_cms.repository.ContentRepository;
import com.project.headless_cms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    public DashboardResponse getDashboardData() {
        long totalArticles = contentRepository.count();
        long publishedArticles = contentRepository.countByStatus(ArticleStatus.PUBLISHED);
        long totalUsers = userRepository.count();

        List<Content> recentArticles = contentRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .limit(5)
                .toList();

        int totalViews = contentRepository.findAll()
                .stream()
                .map(Content::getViews)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();

        DashboardResponse response = new DashboardResponse();
        response.setTotalArticles(totalArticles);
        response.setPublishedArticles(publishedArticles);
        response.setTotalUsers(totalUsers);
        response.setViewsThisMonth(totalViews);
        response.setRecentArticles(recentArticles);

        return response;
    }
}