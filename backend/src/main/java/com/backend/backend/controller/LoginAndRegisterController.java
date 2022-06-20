package com.backend.backend.controller;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.service.LoginAndRegisterService;
import com.backend.backend.vo.LoginVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "LoginAndRegisterController 注册和登陆的接口")
public class LoginAndRegisterController {
    @Autowired
    private LoginAndRegisterService loginAndRegisterService;
    @RequestMapping(value = "/login",method = RequestMethod.POST )
    @ApiOperation(value = "login-登陆")
    @ApiImplicitParam(name = "loginVo", value = "登陆信息", required = true, dataType = "LoginVo")

    public CommonResult<Map<String,String>> userLogin(@RequestBody LoginVo loginVo){
        Map<String,String> tokenMap =loginAndRegisterService.userLogin(loginVo);
        if (Objects.isNull(tokenMap)){
            return CommonResult.failed("登陆失败");
        }
        return CommonResult.success(tokenMap);
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ApiOperation(value = "hello-测试")
    @PreAuthorize("hasAnyAuthority('driver,owner,cargoOwner')")
    public CommonResult<?> hello(){
        return CommonResult.success("hello");
    }



}
