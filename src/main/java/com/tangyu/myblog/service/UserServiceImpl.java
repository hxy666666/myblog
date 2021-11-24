package com.tangyu.myblog.service;

import com.tangyu.myblog.mapper.UserMapper;
import com.tangyu.myblog.pojo.User;
import com.tangyu.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hxy
 * @create 2021-11-10 20:39
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.getUserByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
