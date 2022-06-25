package com.backend.backend.security;

import com.backend.backend.dao.UserMapper;
import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;
import com.backend.backend.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommonUtil commonUtil;

    @Override
    public UserDetails loadUserByUsername(String s){
        //查询用户信息
        User user = userMapper.selectByPrimaryKey(s);
        //判断用户是否存在
        if (Objects.isNull(user)) {
            return null;
        }
        //查询授权信息
        UserDto userDto=new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setPassword(user.getPassword());
        userDto.setUserType(commonUtil.getUserPermissions(user.getUserType()));
        //封装成UserDetails返回
        return new LoginUser(userDto);
    }


}
