package com.lee.controller;

import com.lee.entity.QualityEvaluation;
import com.lee.entity.Score;
import com.lee.entity.User;
import com.lee.mapper.CourseMapper;
import com.lee.mapper.ScoreMapper;
import com.lee.service.CourseService;
import com.lee.service.QualityEvaluationService;
import com.lee.service.ScoreService;
import com.lee.util.SemesterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ScoreRankController {

    @Autowired
    private QualityEvaluationService qualityEvaluationService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassNewsController classNewsController;

    @GetMapping(value = "/score")
    public String getUserScoreInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("msg", "您已掉线，请您重新登录！");
            return "score/scoresRank";
        }

        Integer userId = user.getUserId();
        Map<QualityEvaluation, List<QualityEvaluation>> userScoreInfos = qualityEvaluationService.getUserScoreInfo(userId);
        Map<Integer, String> allCourse = courseService.getAllCourse();
        List<Score> userScores = scoreService.getUserScoreByUserId(userId);
        Map<Integer, Object> userNameById = classNewsController.getUserNameById();
        model.addAttribute("userScoreInfos", userScoreInfos).addAttribute("allCourse", allCourse)
                .addAttribute("userScores", userScores).addAttribute("userNameById", userNameById)
                .addAttribute("courseMap", SemesterUtils.getSemesterMapNumberKey());
        return "score/scoresRank";
    }

}
