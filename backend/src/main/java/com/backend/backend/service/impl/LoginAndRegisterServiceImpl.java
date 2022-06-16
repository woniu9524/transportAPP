package com.backend.backend.service.impl;

import com.backend.backend.dao.UserMapper;
import com.backend.backend.entity.User;
import com.backend.backend.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin() {
        User user= userMapper.selectByPrimaryKey(1);
        return user;
    }
}
