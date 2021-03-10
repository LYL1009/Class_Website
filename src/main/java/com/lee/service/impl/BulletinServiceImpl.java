package com.lee.service.impl;

import com.lee.entity.Bulletin;
import com.lee.entity.ReceiveBulletin;
import com.lee.entity.User;
import com.lee.mapper.BulletinMapper;
import com.lee.mapper.ReceiveBulletinMapper;
import com.lee.mapper.UserMapper;
import com.lee.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    private BulletinMapper bulletinMapper;

    @Autowired
    private ReceiveBulletinMapper receiveBulletinMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<Bulletin, List<Integer>> getAllBulletinsByClassName(User user) {
        Map<Bulletin, List<Integer>> map = new LinkedHashMap<>();
        String className = user.getClassName();
        List<Bulletin> bulletins = bulletinMapper.selectAllBulletinsByClassName(className)==null?new ArrayList<>():bulletinMapper.selectAllBulletinsByClassName(className);
        List<ReceiveBulletin> receiveBulletins = receiveBulletinMapper.selectAll()==null?new ArrayList<>():receiveBulletinMapper.selectAll();
        for (Bulletin bulletin : bulletins) {
            if (bulletin == null) {
                continue;
            }
            List<Integer> list = new ArrayList<>();
            for (ReceiveBulletin receiveBulletin : receiveBulletins) {
                if (receiveBulletin == null) {
                    continue;
                }
                if (bulletin.getBulletinId().equals(receiveBulletin.getBulletinId())) {
                    list.add(receiveBulletin.getUserId());
                }
            }
            map.put(bulletin, list);
        }

        return map;
    }

    @Override
    public Map<String, List<User>> getUserConditionByBulletinId(Integer bulletinId) {
        Map<String, List<User>> map = new HashMap<>();
        List<User> confirmed = new ArrayList<>();
        List<User> unconfirmed = new ArrayList<>();
        map.put("confirmed", confirmed);
        map.put("unconfirmed", unconfirmed);
        Bulletin bulletin = bulletinMapper.selectByBulletinId(bulletinId);
        List<ReceiveBulletin> list = receiveBulletinMapper.selectByBulletinId(bulletinId);
        List<User> users = userMapper.getUsersByClassName(bulletin.getClassName());
        boolean flag;
        for (User user : users) {
            flag = true;
            for (ReceiveBulletin receiveBulletin : list) {
                if (user.getUserId().equals(receiveBulletin.getUserId())) {
                    confirmed.add(user);
                    flag = false;
                    break;
                }
            }
            if (flag) {
                unconfirmed.add(user);
            }
        }
        return map;
    }

    @Override
    public String receiveBulletin(ReceiveBulletin receiveBulletin) {
        int i = receiveBulletinMapper.insert(receiveBulletin);
        if (i > 0) {
            return "success";
        }
        return "error";
    }

}
