package com.lee.service;

import com.lee.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    String saveUserInfo(User user);

    User getUserById(Integer userId);

}
