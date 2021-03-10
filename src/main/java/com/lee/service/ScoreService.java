package com.lee.service;

import com.lee.entity.Score;

import java.util.List;

public interface ScoreService {

    List<Score> getUserScoreByUserId(Integer userId);

}
