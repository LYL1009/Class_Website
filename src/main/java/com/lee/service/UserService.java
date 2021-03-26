package com.lee.service;

import com.lee.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {

    /**
     * 通过用户名获取用户
     *
     * @param username
     * @author Lee
     * @date 2021/3/5 17:15
     * @return com.lee.entity.User
     */
    User getUserByUsername(String username);

    /**
     * 查询所有用户
     *
     * @author Lee
     * @date 2021/3/5 17:15
     * @return java.util.List<com.lee.entity.User>
     */
    List<User> getAllUsers();

    /**
     * 保存学生信息
     * @param user
     * @return
     */
    String saveUserInfo(User user);

    /**
     * 通过id获取学生信息
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    /**
     * 通过班委信息查询该班级学生信息
     * @param user
     * @return
     */
    List<User> getClassUsersByUser(User user);

    /**
     * 通过学生id修改学生状态
     * @param isValid
     * @param userId
     * @return
     */
    int updateUserStatus(String isValid, Integer userId);

    /**
     * 通过userId获取username
     *
     * @author Lee
     * @date 2021/3/5 17:23
     * @return java.util.Map<java.lang.Integer, java.lang.Object>
     */
    Map<Integer, Object> getUserNameMap();

    /**
     * 判断库中不含该学号的user，保存user对象
     * @param user
     * @return
     */
    int saveUser(User user);

}
