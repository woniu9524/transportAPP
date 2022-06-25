package com.backend.backend.filter;

import com.backend.backend.dto.UserDto;
import com.backend.backend.security.LoginUser;
import com.backend.backend.util.RedisUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.List;
import java.util.Objects;

import static com.backend.backend.util.JwtUtil.parseJWT;
@Configuration
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${my.jwtPassword}")
    private String jwtPassword;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //当请求url为/login或者/register时，不进行拦截
        if (httpServletRequest.getRequestURI().equals("/login") || httpServletRequest.getRequestURI().equals("/register")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        //获取token
        String token=httpServletRequest.getHeader("token");
        if (!StringUtils.hasText(token)){
            //当token为空的时候放行(不需要解析了，后面会有其他过滤器，通过不了的)
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //解析token
        String userId;
        List<String> userType;
        try {
            Claims claims = parseJWT(jwtPassword, token);
            userId=claims.getSubject();
            userType= (List<String>) claims.get("userType");
        }catch (Exception e){
            throw new RuntimeException("token非法");//这里JWT过期应该也会抛出
        }

        LoginUser loginUser=null;
        try {
            UserDto userDto=new UserDto();
            userDto.setUserId(userId);
            userDto.setUserType(userType);
            loginUser=new LoginUser(userDto);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
