package com.backend.backend.security.exception;

import com.alibaba.fastjson.JSON;
import com.backend.backend.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException{
        //设置状态码
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        //设置返回内容类型
        httpServletResponse.setContentType("application/json");
        //设置返回内容编码
        httpServletResponse.setCharacterEncoding("UTF-8");
        //设置返回信息
        String json= JSON.toJSONString(CommonResult.unauthorized("认证异常"));
        httpServletResponse.getWriter().write(json);

    }
}
