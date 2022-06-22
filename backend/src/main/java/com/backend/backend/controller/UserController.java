package com.backend.backend.controller;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.service.UserService;
import com.backend.backend.vo.LoginVo;
import com.backend.backend.vo.RegisterVo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/api")
@Api(value = "LoginAndRegisterController 注册和登陆的接口")
public class UserController {
    @Autowired
    private UserService userService;

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

    @RequestMapping(value = "/isLogin",method = RequestMethod.GET)
    @ApiOperation(value = "isLogin-测试是否登陆")
    //@PreAuthorize("hasAnyAuthority('driver,owner,cargoOwner')")
    public CommonResult<?> isLogin(HttpServletRequest request){
        //获取token
        String token = request.getHeader("token");
        return userService.isLogin(token);
    }



}
