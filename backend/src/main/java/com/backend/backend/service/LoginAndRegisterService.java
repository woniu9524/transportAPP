package com.backend.backend.service;


import com.backend.backend.entity.User;
import com.backend.backend.vo.LoginVo;

import java.util.Map;

public interface LoginAndRegisterService {
    public Map<String,String> userLogin(LoginVo loginVo);


}
