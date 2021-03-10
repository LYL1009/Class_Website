package com.lee.entity;

public class ReceiveBulletin {

    private Integer bulletinId;
    private Integer userId;
    private String isReceive;

    public ReceiveBulletin() {
    }

    public ReceiveBulletin(Integer bulletinId, Integer userId, String isReceive) {
        this.bulletinId = bulletinId;
        this.userId = userId;
        this.isReceive = isReceive;
    }

    public Integer getBulletinId() {
        return bulletinId;
    }

    public void setBulletinId(Integer bulletinId) {
        this.bulletinId = bulletinId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(String isReceive) {
        this.isReceive = isReceive;
    }

    @Override
    public String toString() {
        return "ReceiveBulletin{" +
                "bulletinId=" + bulletinId +
                ", userId=" + userId +
                ", isReceive='" + isReceive + '\'' +
                '}';
    }

}
