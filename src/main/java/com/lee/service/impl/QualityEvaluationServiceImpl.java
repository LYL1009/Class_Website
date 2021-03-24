package com.lee.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lee.entity.QualityEvaluation;
import com.lee.mapper.QualityEvaluationMapper;
import com.lee.service.QualityEvaluationService;
import com.lee.util.SemesterUtils;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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
        // 查询综测并靠学期分组
        Map<String, List<QualityEvaluation>> map = new HashMap<>();
        List<QualityEvaluation> qualityEvaluations = qualityEvaluationMapper.selectAll();
        map = qualityEvaluations.stream().collect(Collectors.groupingBy(QualityEvaluation::getSemester));

        // 插入所有学期信息
        List<String> allSemesters = SemesterUtils.getAllSemesters();
        List<String> removeList = new ArrayList<>();
        for (Map.Entry<String, List<QualityEvaluation>> entry : map.entrySet()) {
            if (allSemesters.contains(entry.getKey())) {
                removeList.add(entry.getKey());
            }
        }
        allSemesters.removeAll(removeList);
        for (String semester : allSemesters) {
            map.put(semester, new ArrayList<QualityEvaluation>());
        }

        // 排序
        Map<String, List<QualityEvaluation>> resultMap = new LinkedHashMap<>();
        map.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEachOrdered(x -> resultMap.put(x.getKey(), (x.getValue())));
        return resultMap;
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

    @Override
    public int saveOrUpdateQualityEvaluation(QualityEvaluation qualityEvaluation, String type) {
        String total_score = totalScore(qualityEvaluation);
        qualityEvaluation.setTotalScore(total_score);
        if ("save".equals(type)) {
            int i = qualityEvaluationMapper.insert(qualityEvaluation);
            return i;
        } else if ("update".equals(type)) {
            QualityEvaluation qe = qualityEvaluationMapper.getQualityEvaluationById(qualityEvaluation.getId());
            if (qualityEvaluation.equals(qe)) {
                return 1;
            }
            int update = qualityEvaluationMapper.update(qualityEvaluation);
            return update;
        }
        return 0;
    }

    private String totalScore(QualityEvaluation qualityEvaluation) {
        Double morality = Double.parseDouble(qualityEvaluation.getMorality());
        Double intelligence = Double.parseDouble(qualityEvaluation.getIntelligence());
        Double physique = Double.parseDouble(qualityEvaluation.getPhysique());
        Double aesthetics = Double.parseDouble(qualityEvaluation.getAesthetics());
        Double labour = Double.parseDouble(qualityEvaluation.getLabour());
        Double total = morality * 0.2 + intelligence * 0.6 + physique * 0.08 + aesthetics * 0.06 + labour * 0.06;
        String total_score = new DecimalFormat("#.00").format(total);
        return total_score;
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
