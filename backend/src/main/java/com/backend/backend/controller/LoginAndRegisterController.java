package com.backend.backend.controller;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(value = "LoginAndRegisterController 注册和登陆的接口")
public class LoginAndRegisterController {
    @RequestMapping(value = "/login",method = RequestMethod.POST )
    @ResponseBody
    @ApiOperation(value = "login方法",notes = "详细描述")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password",value = "密码 字符串类型",required = true)
    })
    public String userLogin(
            @ApiParam( name= "用户名", value="登陆时用的用户名",required = true) String username,
            String password){
        return "aaa";
    }
    @ApiIgnore
    @RequestMapping(value = "/login",method = RequestMethod.GET )
    public String userLoginGet(){
        return "aaa";
    }


}
