package com.backend.backend.security.exception;

import com.alibaba.fastjson.JSON;
import com.backend.backend.common.api.CommonResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //设置状态码
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        //设置返回内容类型
        response.setContentType("application/json");
        //设置返回内容编码
        response.setCharacterEncoding("UTF-8");
        //设置返回信息
        String json= JSON.toJSONString(CommonResult.validateFailed("权限不足"));
        response.getWriter().write(json);

    }
}

