package com.lee.service.impl;

import com.lee.entity.CampusEvent;
import com.lee.mapper.CampusEventMapper;
import com.lee.service.CampusEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CampusEventServiceImpl implements CampusEventService {

    @Autowired
    private CampusEventMapper campusEventMapper;

    @Override
    public List<CampusEvent> getAllCampusEvents() {
        Date date = new Date();
        String format = new SimpleDateFormat("yyyyMMdd").format(date);
        List<CampusEvent> campusEvents = campusEventMapper.selectEventByReleaseTime(format);
        return campusEvents;
    }

}
