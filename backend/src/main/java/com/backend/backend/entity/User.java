package com.backend.backend.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class User {
    private String userId;

    private String password;

    private Integer userType;

}