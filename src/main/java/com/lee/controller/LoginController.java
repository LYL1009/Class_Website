package com.lee.controller;

import com.lee.entity.User;
import com.lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            if (username.equals(user.getUsername()) && password.equals(user.getWorkNum())) {
                //登陆成功，防止表单重复提交，可以重定向到主页
                session.setAttribute("user", user);
                return "redirect:/main.html";
            } else {
                map.put("msg", "密码错误！");
                return "login";
            }
        } else {
            map.put("msg", "用户不存在！");
            return "login";
        }
    }

}
