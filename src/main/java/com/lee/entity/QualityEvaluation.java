package com.lee.entity;

import java.util.Objects;

public class QualityEvaluation {

    private Integer id;
    private Integer userId; // 用户id
    private String morality; //  德育素质测评（20%）
    private String intelligence; // 智育素质测评（60%）
    private String physique; // 体育素质测评（8%）
    private String aesthetics; // 美育素质测评（6%）
    private String labour; // 劳育素质测评（6%）
    private String totalScore; // 总分
    private String semester; // 学期

    public QualityEvaluation() {
    }

    public QualityEvaluation(Integer userId, String morality, String intelligence, String physique, String aesthetics, String labour, String semester) {
        this.userId = userId;
        this.morality = morality;
        this.intelligence = intelligence;
        this.physique = physique;
        this.aesthetics = aesthetics;
        this.labour = labour;
        this.semester = semester;
    }

    public QualityEvaluation(Integer userId, String morality, String intelligence, String physique, String aesthetics, String labour, String totalScore, String semester) {
        this.userId = userId;
        this.morality = morality;
        this.intelligence = intelligence;
        this.physique = physique;
        this.aesthetics = aesthetics;
        this.labour = labour;
        this.totalScore = totalScore;
        this.semester = semester;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMorality() {
        return morality;
    }

    public void setMorality(String morality) {
        this.morality = morality;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getPhysique() {
        return physique;
    }

    public void setPhysique(String physique) {
        this.physique = physique;
    }

    public String getAesthetics() {
        return aesthetics;
    }

    public void setAesthetics(String aesthetics) {
        this.aesthetics = aesthetics;
    }

    public String getLabour() {
        return labour;
    }

    public void setLabour(String labour) {
        this.labour = labour;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityEvaluation that = (QualityEvaluation) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(morality, that.morality) && Objects.equals(intelligence, that.intelligence) && Objects.equals(physique, that.physique) && Objects.equals(aesthetics, that.aesthetics) && Objects.equals(labour, that.labour) && Objects.equals(totalScore, that.totalScore) && Objects.equals(semester, that.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, morality, intelligence, physique, aesthetics, labour, totalScore, semester);
    }

    @Override
    public String toString() {
        return "QualityEvaluation{" +
                "id=" + id +
                ", userId=" + userId +
                ", morality='" + morality + '\'' +
                ", intelligence='" + intelligence + '\'' +
                ", physique='" + physique + '\'' +
                ", aesthetics='" + aesthetics + '\'' +
                ", labour='" + labour + '\'' +
                ", totalScore='" + totalScore + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}
