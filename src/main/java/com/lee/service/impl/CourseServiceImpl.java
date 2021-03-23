package com.lee.service.impl;

import com.lee.entity.Course;
import com.lee.mapper.CourseMapper;
import com.lee.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<Integer, String> getAllCourse() {
        Map<Integer, String> map = new HashMap<>();
        List<Course> courses = courseMapper.selectAll();
        for (Course course : courses) {
            map.put(course.getCourseId(), course.getCourseName());
        }
        return map;
    }

    @Override
    public List<Course> getCourseList() {
        return courseMapper.selectAll();
    }

}
