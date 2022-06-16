package com.backend.backend.controller;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.entity.User;
import com.backend.backend.service.LoginAndRegisterService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "LoginAndRegisterController 注册和登陆的接口")
public class LoginAndRegisterController {
    @Autowired
    private LoginAndRegisterService loginAndRegisterService;
    @RequestMapping(value = "/login",method = RequestMethod.GET )
    @ApiOperation(value = "login-登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password",value = "密码 字符串类型",required = true),
            @ApiImplicitParam(name = "password",value = "密码 字符串类型",required = true)
    })
    public CommonResult<User> userLogin(String username, String password){
        User user =loginAndRegisterService.userLogin();
        return CommonResult.success(user);
    }



}
