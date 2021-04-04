package com.pyl.blog.service;

import com.pyl.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
