package com.backend.backend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
public class UserDto {
        private String userId;

        private String password;

        private List<String> userType;

}
