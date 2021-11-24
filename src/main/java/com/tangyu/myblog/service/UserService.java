package com.tangyu.myblog.service;

import com.tangyu.myblog.pojo.User;

/**
 * @author hxy
 * @create 2021-11-10 20:38
 */
public interface UserService {
    User checkUser(String username,String password);
}
