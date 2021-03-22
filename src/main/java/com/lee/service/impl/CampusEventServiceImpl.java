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
        List<CampusEvent> campusEvents = campusEventMapper.selectEventByReleaseTime(getDate());
        return campusEvents;
    }

    public String getDate() {
        Date date = new Date();
        String format = new SimpleDateFormat("yyyyMMdd").format(date);
        return format;
    }

    @Override
    public int saveCampusEvent(CampusEvent campusEvent) {
        String img1 = campusEvent.getEventImg1().length()>29?campusEvent.getEventImg1():null;
        String img2 = campusEvent.getEventImg2().length()>29?campusEvent.getEventImg2():null;
        String img3 = campusEvent.getEventImg3().length()>29?campusEvent.getEventImg3():null;
        if (campusEvent.getEventInfo() != "" && campusEvent.getEventInfo().length() > 0) {
            CampusEvent campusEvent1 = new CampusEvent(campusEvent.getEventInfo(), img1, img2, img3, campusEvent.getReleaseTime());
            int insert = campusEventMapper.insert(campusEvent1);
            return insert;
        }
        return 0;
    }

}
