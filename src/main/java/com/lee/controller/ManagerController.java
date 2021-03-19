package com.lee.controller;

import com.lee.entity.User;
import com.lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/stuManager")
    public String stuManager(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            model.addAttribute("msg", "用户请重新登录");
            return "management/classUsers";
        }
        List<User> list = userService.getClassUsersByUser(user);
        model.addAttribute("users", list);
        return "management/classUsers";
    }

    @PostMapping(value = "/renew")
    @ResponseBody
    public String renew(@RequestParam("userId") Integer userId, @RequestParam("status") String status) {
        int i = userService.updateUserStatus(status, userId);
        if (i > 0) {
            return "success";
        }
        return null;
    }

    @PostMapping(value = "/freeze")
    @ResponseBody
    public String freeze(@RequestParam("userId") Integer userId, @RequestParam("status") String status) {
        int i = userService.updateUserStatus(status, userId);
        if (i > 0) {
            return "success";
        }
        return null;
    }

}
