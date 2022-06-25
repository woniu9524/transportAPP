package com.backend.backend.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("注册视图对象")
public class RegisterVo {

    @ApiModelProperty("用户名")
    private String userId;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("用户类型")
    private Integer userType;
    //姓名
    @ApiModelProperty("姓名")
    private String name;
    //性别
    @ApiModelProperty("性别")
    private String gender;
    //手机号码
    @ApiModelProperty("手机号码")
    private String phone;
    //邮箱
    @ApiModelProperty("邮箱")
    private String email;
    //居住地
    @ApiModelProperty("居住地")
    private String address;
    //验证码
    private String code;


}
