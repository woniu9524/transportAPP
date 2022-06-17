package com.backend.backend;

import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;
import com.backend.backend.security.LoginUser;
import com.backend.backend.util.JwtUtil;
import com.backend.backend.util.RedisUtils;
import io.jsonwebtoken.Claims;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.backend.backend.util.JwtUtil.parseJWT;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
    }

    //密码校验测试
    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    void passwordEncodeUseTest(){
        String encodeS=passwordEncoder.encode("123456");
        System.out.println(encodeS);
        System.out.println(passwordEncoder.matches("123456",encodeS));
    }

    //Jwt校验测试
    @Test
    void jwtUseTest(){
        // 生成jwt
        String jwt = JwtUtil.createJWT("1234567", 10000, "admin"); // 10秒过期
        System.out.println(jwt); // 生成token
        // 解析jwt
        Claims claims = parseJWT("1234567", jwt);
        // 获取用户名信息
        Object username = claims.get("username");
        System.out.println("用户名:"+username); // 解析token
    }

    //redis测试
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtils redisUtils;
    @Test
    public void redisUseTest(){
        UserDto userDto=(UserDto)redisUtils.get("login:1");
        redisTemplate.opsForValue().set("username","pass1");
        System.out.println(redisTemplate.opsForValue().get("username"));
        User user=new User();
        user.setUserId("123");
        user.setPassword("456");
        redisUtils.set("user",user,60*120);
        // 从redis中获取
        System.out.println("获取到数据：" + redisUtils.get("user"));
    }
}
