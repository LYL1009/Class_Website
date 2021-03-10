package com.lee.controller;

import com.lee.entity.User;
import com.lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserInfoController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userInfo")
    public String userInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        User newUser = userService.getUserById(user.getUserId());
        if (!user.equals(newUser)) {
            session.setAttribute("user", user);
        }
        return "user/userInfo";
    }

    @PostMapping(value = "/saveUserInfo")
    @ResponseBody
    public String saveUserInfo(@RequestParam("email") String email, @RequestParam("qq") String qq, HttpSession session,
                               @RequestParam("phone") String phone, @RequestParam("info") String info) {
        User user = (User) session.getAttribute("user");
        user.setEmail(email);
        user.setQq(qq);
        user.setPhone(phone);
        user.setInfo(info);
        return userService.saveUserInfo(user);
    }

}
