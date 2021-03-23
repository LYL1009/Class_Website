package com.lee.service;

import com.lee.entity.Bulletin;
import com.lee.entity.ReceiveBulletin;
import com.lee.entity.User;

import java.util.List;
import java.util.Map;

public interface BulletinService {

    Map<Bulletin, List<Integer>> getAllBulletinsByClassName(User user);

    Map<String, List<User>> getUserConditionByBulletinId(Integer bulletinId);

    String receiveBulletin(ReceiveBulletin receiveBulletin);

    int saveBulletin(Bulletin bulletin);

    String getFormatDate();

}
