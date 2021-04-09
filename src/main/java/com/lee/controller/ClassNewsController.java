package com.lee.controller;

import com.lee.entity.*;
import com.lee.service.CampusEventService;
import com.lee.service.ClassCommentService;
import com.lee.service.ClassNewsService;
import com.lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班级圈和校园新鲜事俩个模块的controller
 * @author Lee
 * @date 2021/3/7 12:33
 */
@Controller
public class ClassNewsController {

    @Autowired
    private ClassNewsService classNewsService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClassCommentService classCommentService;

    @Autowired
    private CampusEventService campusEventService;

    @GetMapping(value = "/classNews")
    public String classNews(Model model) {
        Map<ClassNews, List<ClassComment>> newsAndComments = classNewsService.getNewsAndComments();
        model.addAttribute("users", getUserNameById());
        model.addAttribute("newsAndComments", newsAndComments);
        return "news/list";
    }

    @PostMapping(value = "/saveTalking")
    @ResponseBody
    public String saveNews(ClassNews classNews) {
        return classNewsService.saveNews(classNews);
    }

    @GetMapping(value = "/giveLike")
    public String giveLike(@RequestParam("userId") Integer userId, @RequestParam("newsId") Integer newsId, Model model) {
        NewsLike newsLike = classNewsService.giveLike(userId, newsId);
        model.addAttribute("newsLike", newsLike);
        return "redirect:/classNews";
    }

    @GetMapping(value = "/saveComment")
    public String saveComment(@RequestParam("user_id") Integer userId, @RequestParam("news_id") Integer newsId,
                              @RequestParam("comment-message") String message) {
        ClassComment classComment = new ClassComment(message, userId, newsId);
        classCommentService.saveComment(classComment);
        return "redirect:/classNews";
    }

    @GetMapping(value = "/campusEvent")
    public String campusEvent(Model model) {
        List<CampusEvent> allCampusEvents = campusEventService.getAllCampusEvents();
        model.addAttribute("allCampusEvents", allCampusEvents);
        return "news/campus_event";
    }

    /**
     * 通过userId获取username
     *
     * @author Lee
     * @date 2021/3/5 17:23
     * @return java.util.Map<java.lang.Integer, java.lang.Object>
     */
    public Map<Integer, Object> getUserNameById() {
        Map<Integer, Object> map = new HashMap<>();
        List<User> allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            map.put(user.getUserId(), user.getUsername());
        }
        return map;
    }

}
