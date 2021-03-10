package com.lee.service.impl;

import com.lee.entity.Score;
import com.lee.mapper.ScoreMapper;
import com.lee.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public List<Score> getUserScoreByUserId(Integer userId) {
        return scoreMapper.selectScoreByUserId(userId);
    }
}
