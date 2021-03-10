package com.lee.service.impl;

import com.lee.entity.ClassComment;
import com.lee.mapper.ClassCommentMapper;
import com.lee.service.ClassCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassCommentServiceImpl implements ClassCommentService {

    @Autowired
    private ClassCommentMapper classCommentMapper;

    @Override
    public int saveComment(ClassComment classComment) {
        return classCommentMapper.insert(classComment);
    }

}
