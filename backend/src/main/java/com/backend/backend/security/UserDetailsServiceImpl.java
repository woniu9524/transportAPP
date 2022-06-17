package com.backend.backend.security;

import com.backend.backend.dao.UserMapper;
import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //查询用户信息
        User user = userMapper.selectByPrimaryKey(s);
        //TODO 查询授权信息
        UserDto userDto=new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setPassword(user.getPassword());
        userDto.setUserType(getUserPermissions(user.getUserType()));
        //封装成UserDetails返回
        return new LoginUser(userDto);
    }
    private List<String> getUserPermissions(Integer i){
        //因为表我设计成了123那个，就加一条
        //用户类型：司机0 车主1 车主+司机2 货主3
        List<String> permissions=new ArrayList<>();
        if (Objects.isNull(i)){
            permissions.add("error");
            return permissions;
        }
        switch (i){
            case 0:{
                permissions.add("driver");
                return permissions;
            }
            case 1:{
                permissions.add("owner");
                return permissions;
            }
            case 2:{
                permissions.add("driver");
                permissions.add("owner");
                return permissions;
            }
            case 3:{
                permissions.add("cargoOwner");
                return permissions;
            }
        }
        return permissions;
    }

}
