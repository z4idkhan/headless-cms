package com.project.headless_cms.service;

import com.project.headless_cms.model.Content;
import java.util.List;

public class DashboardResponse {

    private long totalArticles;
    private long publishedArticles;
    private long totalUsers;
    private int viewsThisMonth;
    private List<Content> recentArticles;

    public long getTotalArticles() {
        return totalArticles;
    }

    public void setTotalArticles(long totalArticles) {
        this.totalArticles = totalArticles;
    }

    public long getPublishedArticles() {
        return publishedArticles;
    }

    public void setPublishedArticles(long publishedArticles) {
        this.publishedArticles = publishedArticles;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getViewsThisMonth() {
        return viewsThisMonth;
    }

    public void setViewsThisMonth(int viewsThisMonth) {
        this.viewsThisMonth = viewsThisMonth;
    }

    public List<Content> getRecentArticles() {
        return recentArticles;
    }

    public void setRecentArticles(List<Content> recentArticles) {
        this.recentArticles = recentArticles;
    }
}