package com.lee.service.impl;

import com.lee.entity.ClassComment;
import com.lee.entity.ClassNews;
import com.lee.entity.NewsLike;
import com.lee.mapper.ClassCommentMapper;
import com.lee.mapper.ClassNewsMapper;
import com.lee.mapper.NewsLikeMapper;
import com.lee.service.ClassNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassNewsServiceImpl implements ClassNewsService {

    @Autowired
    private ClassNewsMapper classNewsMapper;

    @Autowired
    private ClassCommentMapper classCommentMapper;

    @Autowired
    private NewsLikeMapper newsLikeMapper;

    @Override
    public Map<ClassNews, List<ClassComment>> getNewsAndComments() {
        // 所有的班级新闻
        List<ClassNews> classNews = classNewsMapper.selectAll();
        // 所有的评论
        List<ClassComment> classComments = classCommentMapper.selectAll();

        // 创建封装新闻和相应评论的集合
        Map<ClassNews, List<ClassComment>> map = new LinkedHashMap<>();
        // 遍历班级新闻
        for (ClassNews classNew : classNews) {
            List<ClassComment> comments = new ArrayList<>();
            Integer newsId = classNew.getNewsId();
            // 遍历评论
            for (ClassComment classComment : classComments) {
                if (newsId.equals(classComment.getNewsId())) {
                    comments.add(classComment);
                }
            }
            map.put(classNew, comments);
        }
        return map;
    }

    @Override
    public NewsLike giveLike(Integer userId, Integer newsId) {
        List<NewsLike> newsLikes = newsLikeMapper.selectNewsLikeByNewsId(newsId);
        ClassNews news = classNewsMapper.selectByPrimaryKey(newsId);
        int i = 0;
        for (NewsLike newsLike : newsLikes) {
            if (userId.equals(newsLike.getUserId()) && "0".equals(newsLike.getIsLike())) {
                i++;
                news.setNewsLikeNum(news.getNewsLikeNum() + 1);
                classNewsMapper.updateByPrimaryKey(news);
                newsLike.setIsLike("1");
                newsLikeMapper.updateIsLikeByNewsLike(newsLike);
                return newsLike;
            } else if (userId.equals(newsLike.getUserId()) && "1".equals(newsLike.getIsLike())) {
                i++;
                news.setNewsLikeNum(news.getNewsLikeNum() - 1);
                classNewsMapper.updateByPrimaryKey(news);
                newsLike.setIsLike("0");
                newsLikeMapper.updateIsLikeByNewsLike(newsLike);
                return newsLike;
            }
        }
        if (i == 0) {
            NewsLike newsLike = new NewsLike(newsId, userId, "1");
            news.setNewsLikeNum(news.getNewsLikeNum() + 1);
            classNewsMapper.updateByPrimaryKey(news);
            newsLikeMapper.insertNewsLike(newsLike);
            return newsLike;
        }
        return null;
    }

    @Override
    public String saveNews(ClassNews classNews) {
        if (classNews.getNewsInfo() != null && classNews.getNewsInfo().length() > 0 && classNews.getUserId() != null) {
            int insert = classNewsMapper.insert(classNews);
            if (insert > 0) {
                return "success";
            }
        }
        return "error";
    }

}
