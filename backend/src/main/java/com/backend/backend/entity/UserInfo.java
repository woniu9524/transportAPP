package com.backend.backend.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户信息")
public class UserInfo {
    private String userId;

    private String name;

    private String gender;

    private String phone;

    private String email;

    private String address;

}