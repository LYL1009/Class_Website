package com.lee.mapper;

import com.lee.entity.NewsLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NewsLikeMapper {

    List<NewsLike> selectNewsLikeByNewsId(Integer newsId);

    int updateIsLikeByNewsLike(NewsLike newsLike);

    int insertNewsLike(NewsLike newsLike);

}
