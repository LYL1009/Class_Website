package com.lee.service;

import com.lee.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {

    Map<Integer, String> getAllCourse();

    List<Course> getCourseList();

}
