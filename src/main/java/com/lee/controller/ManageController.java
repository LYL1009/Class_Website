package com.lee.controller;

import com.alibaba.druid.util.StringUtils;
import com.lee.entity.*;
import com.lee.service.*;
import com.lee.util.SemesterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ManageController {

    public final static String DOMAIN = "http://localhost:8080/";

    @Autowired
    private CampusEventService campusEventService;

    @Autowired
    private UserService userService;

    @Autowired
    private BulletinService bulletinService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private QualityEvaluationService qualityEvaluationService;

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

    @GetMapping(value = "/releaseNews")
    public String releaseNews() {
        return "management/releaseNews";
    }

    /**
     * 校园头条发布，可以上传三张图片，使用type=file形式传到后端(使用MultipartFile类接收)
     * @param body
     * @param image1
     * @param image2
     * @param image3
     * @param request
     * @return
     */
    @PostMapping("/saveNews")
    public String uploadImage(@RequestParam("body") String body, @RequestParam("file1") MultipartFile image1,
                              @RequestParam("file2") MultipartFile image2,
                              @RequestParam("file3") MultipartFile image3, HttpServletRequest request) {
        //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String fileName1 = image1.getOriginalFilename();  //获取文件名
        String fileName2 = image2.getOriginalFilename();  //获取文件名
        String fileName3 = image3.getOriginalFilename();  //获取文件名

        // 图片存储目录及图片名称
        String url_path1 = "images" + File.separator + fileName1;
        String url_path2 = "images" + File.separator + fileName2;
        String url_path3 = "images" + File.separator + fileName3;
        //图片保存路径
        String savePath1 = staticPath + File.separator + url_path1;
        String savePath2 = staticPath + File.separator + url_path2;
        String savePath3 = staticPath + File.separator + url_path3;
        System.out.println("图片1保存地址：" + savePath1);
        // 访问路径=静态资源路径+文件目录路径
        String visitPath1 = "static/" + url_path1;
        System.out.println("图片1访问uri：" + visitPath1);

        CampusEvent campusEvent = new CampusEvent(body, DOMAIN + url_path1, DOMAIN + url_path2, DOMAIN + url_path3, campusEventService.getDate());
        int i = campusEventService.saveCampusEvent(campusEvent);

        File saveFile1 = new File(savePath1);
        File saveFile2 = new File(savePath2);
        File saveFile3 = new File(savePath3);
        if (!saveFile1.exists()) {
            saveFile1.mkdirs();
        }
        try {
            if (savePath1.length() > 67) {
                image1.transferTo(saveFile1);  //将临时存储的文件移动到真实存储路径下
            }
            if (savePath2.length() > 67) {
                image2.transferTo(saveFile2);
            }
            if (savePath3.length() > 67) {
                image3.transferTo(saveFile3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (i > 0) {
            return "redirect:/campusEvent";
        }

        return "redirect:/releaseNews";
    }

    @GetMapping(value = "/releaseBulletin")
    public String releaseBulletin() {
        return "management/releaseBulletin";
    }

    @PostMapping("/saveBulletin")
    public String saveBulletin(@RequestParam("userId") Integer userId, @RequestParam("className") String className,
                               @RequestParam("head") String head, @RequestParam("body") String body,
                               @RequestParam("img") MultipartFile img, HttpServletRequest request) {
        //获取项目classes/static的地址
        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String fileName1 = img.getOriginalFilename();  //获取文件名

        // 图片存储目录及图片名称
        String url_path1 = "images" + File.separator + fileName1;
        //图片保存路径
        String savePath1 = staticPath + File.separator + url_path1;
        System.out.println("图片1保存地址：" + savePath1);
        // 访问路径=静态资源路径+文件目录路径
        String visitPath1 = "static/" + url_path1;
        System.out.println("图片1访问uri：" + visitPath1);

        Bulletin bulletin = new Bulletin(userId, head, body, DOMAIN + url_path1, bulletinService.getFormatDate(), className);
        int i = bulletinService.saveBulletin(bulletin);

        File saveFile1 = new File(savePath1);
        if (!saveFile1.exists()) {
            saveFile1.mkdirs();
        }
        try {
            if (savePath1.length() > 67) {
                img.transferTo(saveFile1);  //将临时存储的文件移动到真实存储路径下
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (i > 0) {
            return "redirect:/classBulletin";
        }

        return "redirect:/releaseBulletin";
    }

    @GetMapping(value = "/scoreManagement")
    public String scoreManagement(Model model) {
        Map<String, List<QualityEvaluation>> qualityEvaluations = qualityEvaluationService.getQualityEvaluations();
        List<Course> courseList = courseService.getCourseList();
        model.addAttribute("courseList", courseList).addAttribute("qualityEvaluations", qualityEvaluations)
                .addAttribute("courseMap", SemesterUtils.getSemesterMapNumberKey()).addAttribute("userMap", userService.getUserNameMap());
        return "management/scoreManagement";
    }

    @GetMapping(value = "/addQualityEvaluation")
    public String addQualityEvaluation() {
        return "management/addQualityEvaluation";
    }

    @GetMapping(value = "/updateScore")
    public String updateScore(@RequestParam("userId") Integer userId,
                              @RequestParam("semester") String semester, Model model) {
        QualityEvaluation qualityEvaluation = qualityEvaluationService.getQualityEvaluationByUserIdAndSemester(userId, semester);
        if (null == qualityEvaluation) {
            return null;
        }
        model.addAttribute("qualityEvaluation", qualityEvaluation).addAttribute("userMap", userService.getUserNameMap());
        return "management/updateQualityEvaluation";
    }

    @GetMapping(value = "/addScore")
    public String addScore(@RequestParam("semester") String semester, Model model) {
        List<Course> courseList = courseService.getCourseList();
        model.addAttribute("semester", semester).addAttribute("courseList", courseList);
        return "management/addScore";
    }

    @PostMapping(value = "/saveQualityEvaluation")
    public String saveQualityEvaluation(@RequestParam("username") String username, @RequestParam("semester") String semester,
                                        @RequestParam("morality") String morality, @RequestParam("intelligence") String intelligence,
                                        @RequestParam("physique") String physique, @RequestParam("aesthetics") String aesthetics,
                                        @RequestParam("labour") String labour) {
        Integer userId = null;
        for (Map.Entry<Integer, Object> entry : userService.getUserNameMap().entrySet()) {
            if (StringUtils.equals(username, entry.getValue() + "")) {
                userId = entry.getKey();
                break;
            }
        }
        QualityEvaluation qualityEvaluation = new QualityEvaluation(userId, morality, intelligence, physique, aesthetics, labour, semester);
        int save = qualityEvaluationService.saveOrUpdateQualityEvaluation(qualityEvaluation, "save");
        if (save > 0) {
            return "redirect:/scoreManagement";
        }
        return null;
    }

    @PostMapping(value = "/updateQualityEvaluation")
    public String updateQualityEvaluation(QualityEvaluation qualityEvaluation) {
        int update = qualityEvaluationService.saveOrUpdateQualityEvaluation(qualityEvaluation, "update");
        if (update > 0) {
            return "redirect:/scoreManagement";
        }
        return null;
    }

    @PostMapping(value = "/saveScore")
    @ResponseBody
    public String saveScore(Score score, @RequestParam("username") String username) {
        for (Map.Entry<Integer, Object> entry : userService.getUserNameMap().entrySet()) {
            if (StringUtils.equals(username, entry.getValue() + "")) {
                score.setUserId(entry.getKey());
                break;
            }
        }
        int i = scoreService.saveScore(score);
        if (i > 0) {
            return "success";
        }
        return "";
    }
}
