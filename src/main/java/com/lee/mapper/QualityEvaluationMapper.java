package com.lee.mapper;

import com.lee.entity.QualityEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QualityEvaluationMapper {

    List<QualityEvaluation> selectAll();

    List<QualityEvaluation> selectAllBySemester(String semester);

    List<QualityEvaluation> selectAllByUserId(Integer userId);

}
