package com.backend.backend.service;


import com.backend.backend.common.api.CommonResult;
import com.backend.backend.vo.LoginVo;
import com.backend.backend.vo.RegisterVo;

import java.util.Map;

public interface UserService {
    public Map<String,String> userLogin(LoginVo loginVo);


    Map<String, String> userRegister(RegisterVo registerVo);

    CommonResult<?> isLogin(String token);
}
