package com.lee.service;

import com.lee.entity.CampusEvent;

import java.util.List;

public interface CampusEventService {

    List<CampusEvent> getAllCampusEvents();

    String getDate();

    int saveCampusEvent(CampusEvent campusEvent);

}
