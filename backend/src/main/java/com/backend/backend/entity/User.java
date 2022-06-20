package com.backend.backend.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@ApiModel("用户")
public class User {
    private String userId;

    private String password;

    private Integer userType;

}