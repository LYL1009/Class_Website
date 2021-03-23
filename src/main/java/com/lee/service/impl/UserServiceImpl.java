package com.lee.service.impl;

import com.lee.entity.User;
import com.lee.mapper.UserMapper;
import com.lee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            return null;
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getIsValid()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), passwordEncoder.encode(user.getWorkNum()), authorities);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public String saveUserInfo(User user) {
        int i = userMapper.updateUserInfo(user);
        if (i > 0) {
            return "success";
        }
        return "error";
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public List<User> getClassUsersByUser(User user) {
        if (user != null) {
            return userMapper.getUsersByClassName(user.getClassName());
        }
        return null;
    }

    @Override
    public int updateUserStatus(String isValid, Integer userId) {
        User user = userMapper.getUserById(userId);
        user.setIsValid(isValid);
        int i = userMapper.updateUserInfo(user);
        return i;
    }

    /**
     * 通过userId获取username
     *
     * @author Lee
     * @date 2021/3/5 17:23
     * @return java.util.Map<java.lang.Integer, java.lang.Object>
     */
    @Override
    public Map<Integer, Object> getUserNameMap() {
        Map<Integer, Object> map = new HashMap<>();
        List<User> allUsers = userMapper.selectAll();
        for (User user : allUsers) {
            map.put(user.getUserId(), user.getUsername());
        }
        return map;
    }

}
