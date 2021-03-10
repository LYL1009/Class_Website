package com.lee.controller;

import com.lee.entity.Bulletin;
import com.lee.entity.ReceiveBulletin;
import com.lee.entity.User;
import com.lee.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ClassBulletinController {

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private ClassNewsController classNewsController;

    @GetMapping(value = "/classBulletin")
    public String getAllBulletins(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Map<Bulletin, List<Integer>> allBulletins = bulletinService.getAllBulletinsByClassName(user);
        Map<Integer, Object> users = classNewsController.getUserNameById();
        model.addAttribute("allBulletins", allBulletins).addAttribute("users", users);
        return "bulletin/classBulletin";
    }

    @GetMapping(value = "/getReceiveUser")
    public String getReceiveUser(@RequestParam ("bulletinId") Integer bulletinId, Model model) {
        Map<String, List<User>> userCondition = bulletinService.getUserConditionByBulletinId(bulletinId);
        model.addAttribute("userCondition", userCondition);
        return "bulletin/userCondition";
    }

    @PostMapping(value = "/receiveBulletin")
    @ResponseBody
    public String receiveBulletin(@RequestParam ("userId") Integer userId, @RequestParam("bulletinId") Integer bulletinId) {
        ReceiveBulletin receiveBulletin = new ReceiveBulletin(bulletinId, userId, "1");
        return bulletinService.receiveBulletin(receiveBulletin);
    }

}
