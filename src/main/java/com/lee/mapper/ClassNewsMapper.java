package com.lee.mapper;

import com.lee.entity.ClassNews;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ClassNewsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_news
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer newsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_news
     *
     * @mbg.generated
     */
    int insert(ClassNews record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_news
     *
     * @mbg.generated
     */
    ClassNews selectByPrimaryKey(Integer newsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_news
     *
     * @mbg.generated
     */
    List<ClassNews> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_class_news
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ClassNews record);
}