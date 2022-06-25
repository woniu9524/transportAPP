package com.backend.backend;

import com.backend.backend.dto.UserDto;
import com.backend.backend.entity.User;
import com.backend.backend.util.JwtUtil;
import com.backend.backend.util.RedisUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.backend.backend.util.JwtUtil.parseJWT;


import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

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
//        // 生成jwt
//        String jwt = JwtUtil.createJWT("1234567", 10000,"admin","admin"); // 10秒过期
//        System.out.println(jwt); // 生成token
//        // 解析jwt
//        Claims claims = parseJWT("1234567", jwt);
//        // 获取用户名信息
//        Object username = claims.get("username");
//        System.out.println("用户名:"+username); // 解析token
    }

    //redis测试
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
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



    @Value(value = "${Qiniu.AccessKey}")
    private String accessKey;
    @Value(value = "${Qiniu.SecretKey}")
    private String secretKey;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //测试文件上传
    @Test
    public void testUpload() {
        //构造一个带指定 Region 对象的配置类，指定存储区域，和存储空间选择的区域一致
        Configuration cfg = new Configuration(Region.huanan());
        //其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //生成上传凭证，然后准备上传
        String bucket = "transport";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID() + ".png";
        FileInputStream fileInputStream = null;
        try {

            String filePath = "C:\\Users\\zhangcheng\\Pictures\\IMG_0592(20220426-112026).JPG";
            fileInputStream = new FileInputStream(new File(filePath));
            //得到本地文件的字节数组
            byte[] bytes = IOUtils.toByteArray(fileInputStream);
            //认证
            Auth auth = Auth.create(accessKey, secretKey);
            //认证通过后得到token（令牌）
            String upToken = auth.uploadToken(bucket);
            try {
                //上传文件,参数：字节数组，key，token令牌
                //key: 建议我们自已生成一个不重复的名称
                Response response = uploadManager.put(bytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = JSON.parseObject(response.bodyString(),
                        DefaultPutRet.class);
                logger.info("上传成功{}", JSON.toJSONString(putRet));
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    logger.error("上传失败{}",ex2.getMessage());
                }
            }
        } catch (IOException ex) {
            logger.error("上传文件失败", ex);
        }

    }
}
