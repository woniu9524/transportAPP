package com.backend.backend.controller;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.service.FileService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.backend.backend.util.JwtUtil.parseJWT;

@RestController
@Api(value = "FileController 文件上传的接口")
public class FileController {

    //默认上传到的路径
    @Autowired
    FileService fileService;
    @Value("${my.jwtPassword}")
    private String jwtPassword;

    @PostMapping("/headPhoto")
    public CommonResult<?> upload(@RequestParam("file") MultipartFile uploadFile, HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = parseJWT(jwtPassword, token);
        String userId = claims.getSubject();
        return fileService.saveFile(uploadFile, userId);
    }

    @GetMapping("/headPhoto")
    public CommonResult<?> load(HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = parseJWT(jwtPassword, token);
        String userId = claims.getSubject();
        return fileService.loadImage(userId);
    }

}
