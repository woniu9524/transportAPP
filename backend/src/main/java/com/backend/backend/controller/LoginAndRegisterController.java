package com.backend.backend.controller;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.entity.User;
import com.backend.backend.service.LoginAndRegisterService;
import com.backend.backend.vo.LoginVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "LoginAndRegisterController 注册和登陆的接口")
public class LoginAndRegisterController {
    @Autowired
    private LoginAndRegisterService loginAndRegisterService;
    @RequestMapping(value = "/login",method = RequestMethod.POST )
    @ApiOperation(value = "login-登陆")

    public CommonResult<Map<String,String>> userLogin(@RequestBody LoginVo loginVo){
        Map<String,String> tokenMap =loginAndRegisterService.userLogin(loginVo);
        if (Objects.isNull(tokenMap)){
            return CommonResult.failed("账号密码错误");
        }
        return CommonResult.success(tokenMap);
    }



}
