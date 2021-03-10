package com.lee.entity;

public class NewsLike {

    private Integer newsId;
    private Integer userId;
    private String isLike;

    public NewsLike() {
    }

    public NewsLike(Integer newsId, Integer userId, String isLike) {
        this.newsId = newsId;
        this.userId = userId;
        this.isLike = isLike;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    @Override
    public String toString() {
        return "NewsLike{" +
                "newsId=" + newsId +
                ", userId=" + userId +
                ", isLike='" + isLike + '\'' +
                '}';
    }

}
