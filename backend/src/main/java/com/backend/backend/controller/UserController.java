package com.backend.backend.controller;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.service.UserService;
import com.backend.backend.vo.LoginVo;
import com.backend.backend.vo.RegisterVo;
import com.backend.backend.vo.SendEmailVo;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.backend.backend.util.JwtUtil.parseJWT;

@RestController
@Api(value = "LoginAndRegisterController 注册和登陆的接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${my.jwtPassword}")
    private String jwtPassword;
    //登陆
    @RequestMapping(value = "/login",method = RequestMethod.POST )
    @ApiOperation(value = "login-登陆")
    @ApiImplicitParam(name = "loginVo", value = "登陆信息", required = true, dataType = "LoginVo")
    public CommonResult<?> userLogin(@RequestBody LoginVo loginVo){
        return userService.userLogin(loginVo);
    }

    //注册
    @RequestMapping(value = "/register",method = RequestMethod.POST )
    @ApiOperation(value = "register-注册")
    @ApiImplicitParam(name = "registerVo", value = "注册信息", required = true, dataType = "RegisterVo")
    public CommonResult<?> userRegister(@RequestBody RegisterVo registerVo){
        return userService.userRegister(registerVo);
    }
    //发送验证码
    @RequestMapping(value = "/sendEmail",method = RequestMethod.POST )
    @ApiOperation(value = "register-发送验证码")
    public CommonResult<?> sendEmail(@RequestBody SendEmailVo sendEmailVo){
        return userService.sendEmail(sendEmailVo.getUserId(),sendEmailVo.getEmail());
    }

    //判断是否登陆
    @RequestMapping(value = "/isLogin",method = RequestMethod.GET)
    @ApiOperation(value = "isLogin-测试是否登陆")
    //@PreAuthorize("hasAnyAuthority('driver,owner,cargoOwner')")
    public CommonResult<?> isLogin(HttpServletRequest request){
        //获取token
        String token = request.getHeader("token");
        return userService.isLogin(token);
    }

    //获取用户信息
    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public CommonResult<?> getUserInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        Claims claims = parseJWT(jwtPassword, token);
        String userId = claims.getSubject();
        return userService.getUserInfo(userId);

    }

}
