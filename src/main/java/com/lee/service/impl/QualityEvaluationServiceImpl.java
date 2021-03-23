package com.lee.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lee.entity.QualityEvaluation;
import com.lee.mapper.QualityEvaluationMapper;
import com.lee.service.QualityEvaluationService;
import com.lee.util.SemesterUtils;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QualityEvaluationServiceImpl implements QualityEvaluationService {

    @Autowired
    private QualityEvaluationMapper qualityEvaluationMapper;

    @Override
    public Map<QualityEvaluation, List<QualityEvaluation>> getUserScoreInfo(Integer userId) {
        Map<QualityEvaluation, List<QualityEvaluation>> map = new HashMap<>();
        // 获取库中所有数据
        List<QualityEvaluation> list = qualityEvaluationMapper.selectAll();
        // 获取用户获得成绩的学期
        List<String> semester = getUserSemester(userId);
        for (String s : semester) {
            List<QualityEvaluation> qualityEvaluations = new ArrayList<>();
            QualityEvaluation qe = new QualityEvaluation();
            for (QualityEvaluation qualityEvaluation : list) {
                if (s.equals(qualityEvaluation.getSemester())) {
                    qualityEvaluations.add(qualityEvaluation);
                }
                if (userId.equals(qualityEvaluation.getUserId()) && s.equals(qualityEvaluation.getSemester())) {
                    qe = qualityEvaluation;
                }
            }
            map.put(qe, qualityEvaluations);
        }
        return map;
    }

    @Override
    public Map<String, List<QualityEvaluation>> getQualityEvaluations() {
        Map<String, List<QualityEvaluation>> map = new HashMap<>();
        List<QualityEvaluation> qualityEvaluations = qualityEvaluationMapper.selectAll();
        map = qualityEvaluations.stream().collect(Collectors.groupingBy(QualityEvaluation::getSemester));
//        map.forEach((k, v) -> {
//            v = v.stream().sorted(Comparator.comparing(QualityEvaluation::getTotalScore)
//                    .reversed().thenComparing(QualityEvaluation::getIntelligence).reversed())
//                    .collect(Collectors.toList());
//        });
        return map;
    }

    @Override
    public QualityEvaluation getQualityEvaluationByUserIdAndSemester(Integer userId, String semester) {
        List<QualityEvaluation> qualityEvaluations = qualityEvaluationMapper.selectAllByUserId(userId);
        for (QualityEvaluation qualityEvaluation : qualityEvaluations) {
            if (StringUtils.equals(semester, qualityEvaluation.getSemester())) {
                return qualityEvaluation;
            }
        }
        return null;
    }

    private List<String> getUserSemester(Integer userId) {
        List<String> result = new ArrayList<>();
        List<QualityEvaluation> list = qualityEvaluationMapper.selectAllByUserId(userId);
        for (QualityEvaluation qualityEvaluation : list) {
            result.add(qualityEvaluation.getSemester());
        }
        return result;
    }
}
