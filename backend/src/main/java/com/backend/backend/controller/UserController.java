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
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "LoginAndRegisterController 注册和登陆的接口")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST )
    @ApiOperation(value = "login-登陆")
    @ApiImplicitParam(name = "loginVo", value = "登陆信息", required = true, dataType = "LoginVo")
    public CommonResult<Map<String,String>> userLogin(@RequestBody LoginVo loginVo){
        Map<String,String> tokenMap = userService.userLogin(loginVo);
        if (Objects.isNull(tokenMap)){
            return CommonResult.failed("登陆失败");
        }
        return CommonResult.success(tokenMap);
    }

    //注册
    @RequestMapping(value = "/register",method = RequestMethod.POST )
    @ApiOperation(value = "register-注册")
    @ApiImplicitParam(name = "registerVo", value = "注册信息", required = true, dataType = "RegisterVo")
    public CommonResult<Map<String,String>> userRegister(@RequestBody RegisterVo registerVo){
        Map<String,String> tokenMap = userService.userRegister(registerVo);
        if (Objects.isNull(tokenMap)){
            return CommonResult.failed("注册失败-用户名已存在");
        }
        return CommonResult.success(tokenMap);
    }

    //测试
    @RequestMapping(value = "/isLogin",method = RequestMethod.GET)
    @ApiOperation(value = "isLogin-测试是否登陆")
    //@PreAuthorize("hasAnyAuthority('driver,owner,cargoOwner')")
    public CommonResult<?> isLogin(HttpServletRequest request){
        //获取token
        String token = request.getHeader("token");
        return userService.isLogin(token);
    }



}
