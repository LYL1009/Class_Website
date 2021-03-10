package com.lee.mapper;

import com.lee.entity.ReceiveBulletin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ReceiveBulletinMapper {

    List<ReceiveBulletin> selectAll();

    List<ReceiveBulletin> selectByBulletinId(Integer bulletinId);

    int insert(ReceiveBulletin receiveBulletin);

}
