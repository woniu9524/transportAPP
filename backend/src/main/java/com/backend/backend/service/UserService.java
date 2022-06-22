package com.backend.backend.service;


import com.backend.backend.common.api.CommonResult;
import com.backend.backend.vo.LoginVo;
import com.backend.backend.vo.RegisterVo;

import java.util.Map;

public interface UserService {
    public CommonResult<?> userLogin(LoginVo loginVo);


    CommonResult<?> userRegister(RegisterVo registerVo);

    CommonResult<?> isLogin(String token);
}
