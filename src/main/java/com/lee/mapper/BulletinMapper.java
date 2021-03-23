package com.lee.mapper;

import com.lee.entity.Bulletin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BulletinMapper {

    List<Bulletin> selectAllBulletinsByClassName(String className);

    Bulletin selectByBulletinId(Integer bulletinId);

    int insert(Bulletin bulletin);

}
