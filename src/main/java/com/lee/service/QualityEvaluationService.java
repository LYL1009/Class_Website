package com.lee.service;

import com.lee.entity.QualityEvaluation;

import java.util.List;
import java.util.Map;

public interface QualityEvaluationService {

    Map<QualityEvaluation, List<QualityEvaluation>> getUserScoreInfo(Integer userId);

    Map<String, List<QualityEvaluation>> getQualityEvaluations();

    QualityEvaluation getQualityEvaluationByUserIdAndSemester(Integer userId, String semester);

    int saveOrUpdateQualityEvaluation(QualityEvaluation qualityEvaluation, String type);

}
