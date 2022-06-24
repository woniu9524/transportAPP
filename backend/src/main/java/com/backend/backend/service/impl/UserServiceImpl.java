package com.backend.backend.service.impl;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.dao.UserInfoMapper;
import com.backend.backend.dao.UserMapper;
import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;
import com.backend.backend.entity.UserInfo;
import com.backend.backend.security.LoginUser;
import com.backend.backend.service.UserService;
import com.backend.backend.util.JwtUtil;
import com.backend.backend.util.RedisUtils;
import com.backend.backend.vo.LoginVo;
import com.backend.backend.vo.RegisterVo;
import io.jsonwebtoken.Claims;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.backend.backend.util.JwtUtil.parseJWT;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RedisUtils redisUtils;
    @Value("${my.jwtPassword}")
    private String jwtPassword;
    @Value("${my.clearTime}")
    private int clearTime;
    //登陆
    @Override
    public CommonResult<?> userLogin(LoginVo loginVo) {

        //AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginVo.getUserId(),loginVo.getPassword());
        Authentication authentication=null;
        try {
            authentication=authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){
            return CommonResult.failed("登录失败");
        }
        //如果没通过给予提示
        if (Objects.isNull(authentication)){
            return CommonResult.failed("登录失败");
        }
        //通过了，用userid生成一个jwt
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getUserId();
        String token = JwtUtil.createJWT(jwtPassword, clearTime, userId); // 两小时过期
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("user_type",loginUser.getUser().getUserType().get(0));
        //把完整信息存入redis userid为key
        redisUtils.set("login:"+userId,loginUser.getUser(),clearTime);
        return CommonResult.success(tokenMap);
    }

    @Override
    public CommonResult<?> userRegister(RegisterVo registerVo) {
        Map<String,String> tokenMap=new HashMap<>();
        String userId = registerVo.getUserId();
        //查询是否已经存在
        if (userMapper.selectByPrimaryKey(userId)!=null){
            return CommonResult.failed("注册失败-用户名已存在");
        }
        //将LoginVo里的属性赋值给user和user_info
        User user=new User();
        UserInfo userInfo=new UserInfo();
        BeanUtils.copyProperties(registerVo,user);
        BeanUtils.copyProperties(registerVo,userInfo);
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //插入数据库
        userMapper.insert(user);
        userInfoMapper.insert(userInfo);
        //生成jwt
        String token = JwtUtil.createJWT(jwtPassword, clearTime, userId); // 两小时过期
        tokenMap.put("token",token);
        tokenMap.put("user_type",String.valueOf(user.getUserType()));
        //把完整信息存入redis userid为key
        redisUtils.set("login:"+userId,user,clearTime);
        return CommonResult.success(tokenMap);
    }

    //判断是否登陆
    @Override
    public CommonResult<?> isLogin(String token) {
        //获取用户id
        Claims claims = parseJWT(jwtPassword, token);
        String userId = claims.getSubject();
        //查询redis是否存在
        UserDto userDto=(UserDto) redisUtils.get("login:"+userId);
        Map<String,String> typeMap=new HashMap<>();
        if (!Objects.isNull(userDto)){
            typeMap.put("userType",userDto.getUserType().get(0));
            return CommonResult.success(typeMap);
        }
        return CommonResult.failed("未登录");
    }
}
