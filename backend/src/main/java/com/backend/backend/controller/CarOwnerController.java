package com.backend.backend.controller;

import com.backend.backend.common.api.CommonResult;
import com.backend.backend.entity.CargoOrder;
import com.backend.backend.service.CarOwnerService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static com.backend.backend.util.JwtUtil.parseJWT;

@RestController
@Api(value = "CarOwnerController 车主接口")
public class CarOwnerController {
    @Autowired
    private CarOwnerService carOwnerService;
    @Value("${my.jwtPassword}")
    private String jwtPassword;
    //获取车辆信息
    @GetMapping("/carInfo")
    public CommonResult<?> getCarInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        Claims claims = parseJWT(jwtPassword, token);
        String userId = claims.getSubject();
        return carOwnerService.getCarInfo(userId);
    }

    //提交报表
    @PostMapping("/updateOrder")
    public CommonResult<?> updateOrder( @RequestBody CargoOrder cargoOrder) {
        return carOwnerService.updateOrder(cargoOrder);
    }
    //获取订单信息
    @PostMapping("/getOrder")
    public CommonResult<?> getOrder(@RequestBody Map<String,String> map) {
        String carId=map.get("carId");
        return carOwnerService.getOrder(carId);
    }
}
