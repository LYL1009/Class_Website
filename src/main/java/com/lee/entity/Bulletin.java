package com.lee.entity;

import java.util.Objects;

public class Bulletin {

    private Integer bulletinId;
    private Integer userId;
    private String bulletinHead;
    private String bulletinBody;
    private String bulletinImg;
    private String releaseTime;
    private String className;

    public Bulletin() {
    }

    public Bulletin(Integer userId, String bulletinHead, String bulletinBody, String bulletinImg, String releaseTime, String className) {
        this.userId = userId;
        this.bulletinHead = bulletinHead;
        this.bulletinBody = bulletinBody;
        this.bulletinImg = bulletinImg;
        this.releaseTime = releaseTime;
        this.className = className;
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

    public String getBulletinHead() {
        return bulletinHead;
    }

    public void setBulletinHead(String bulletinHead) {
        this.bulletinHead = bulletinHead;
    }

    public String getBulletinBody() {
        return bulletinBody;
    }

    public void setBulletinBody(String bulletinBody) {
        this.bulletinBody = bulletinBody;
    }

    public String getBulletinImg() {
        return bulletinImg;
    }

    public void setBulletinImg(String bulletinImg) {
        this.bulletinImg = bulletinImg;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bulletin bulletin = (Bulletin) o;
        return Objects.equals(bulletinId, bulletin.bulletinId) && Objects.equals(userId, bulletin.userId) && Objects.equals(bulletinHead, bulletin.bulletinHead) && Objects.equals(bulletinBody, bulletin.bulletinBody) && Objects.equals(bulletinImg, bulletin.bulletinImg) && Objects.equals(releaseTime, bulletin.releaseTime) && Objects.equals(className, bulletin.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bulletinId, userId, bulletinHead, bulletinBody, bulletinImg, releaseTime, className);
    }

    @Override
    public String toString() {
        return "Bulletin{" +
                "bulletinId=" + bulletinId +
                ", userId=" + userId +
                ", bulletinHead='" + bulletinHead + '\'' +
                ", bulletinBody='" + bulletinBody + '\'' +
                ", bulletinImg='" + bulletinImg + '\'' +
                ", releaseTime='" + releaseTime + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

}

