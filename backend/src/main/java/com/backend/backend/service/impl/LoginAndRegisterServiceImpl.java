package com.backend.backend.service.impl;

import com.backend.backend.dao.UserMapper;
import com.backend.backend.entity.User;
import com.backend.backend.security.LoginUser;
import com.backend.backend.service.LoginAndRegisterService;
import com.backend.backend.util.JwtUtil;
import com.backend.backend.util.RedisUtils;
import com.backend.backend.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAndRegisterServiceImpl implements LoginAndRegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Map<String,String> userLogin(LoginVo loginVo) {
        //AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginVo.getUserId(),loginVo.getPassword());
        Authentication authentication=null;
        try {
            authentication=authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){
            return null;
        }
        //如果没通过给予提示
        if (Objects.isNull(authentication)){
            return null;
        }
        //通过了，用userid生成一个jwt
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getUserId();
        String token = JwtUtil.createJWT("1234567", 60000*120, userId); // 两小时过期
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        //把完整信息存入redis userid为key
        redisUtils.set("login:"+userId,loginUser.getUser(),60*120);
        return tokenMap;
    }
}
