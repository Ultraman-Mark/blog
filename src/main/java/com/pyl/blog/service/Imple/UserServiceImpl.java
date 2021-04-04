package com.pyl.blog.service.Imple;

import com.pyl.blog.dao.UserRepository;
import com.pyl.blog.po.User;
import com.pyl.blog.service.UserService;
import com.pyl.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
//        extends RuntimeException()
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}
