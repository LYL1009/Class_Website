package com.lee.service;

import com.lee.entity.ClassComment;
import com.lee.entity.ClassNews;
import com.lee.entity.NewsLike;

import java.util.List;
import java.util.Map;

public interface ClassNewsService {

    /**
     *
     * 获取数据库中所有的班级新闻，并附上相应的评论
     * @author Lee
     * @date 2021/3/5 15:27
     * @return java.util.List<java.util.Map < com.lee.entity.ClassNews, com.lee.entity.ClassComment>>
     */
    Map<ClassNews, List<ClassComment>> getNewsAndComments();

    /**
     * 点赞方法
     *
     * @param userId
     * @param newsId
     * @author Lee
     * @date 2021/3/5 17:53
     * @return java.lang.String
     */
    NewsLike giveLike(Integer userId, Integer newsId);

}
