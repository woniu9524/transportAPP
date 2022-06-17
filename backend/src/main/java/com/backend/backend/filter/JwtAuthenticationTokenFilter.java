package com.backend.backend.filter;

import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;
import com.backend.backend.security.LoginUser;
import com.backend.backend.util.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.backend.backend.util.JwtUtil.parseJWT;
@Configuration
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token=httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)){
            //当token为空的时候放行(不需要解析了，后面会有其他过滤器，通过不了的)
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //解析token
        String userid;
        try {
            Claims claims = parseJWT("1234567", token);
            userid=claims.getSubject();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("token非法");//这里JWT过期应该也会抛出
        }
        //从redis获取用户信息
        String redisKey="login:"+userid;
        LoginUser loginUser=null;
        try {
            UserDto user=(UserDto)redisUtils.get(redisKey);
            loginUser=new LoginUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //TODO 存入SecurityContextHolder

        //获取权限分装到Authentication
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
