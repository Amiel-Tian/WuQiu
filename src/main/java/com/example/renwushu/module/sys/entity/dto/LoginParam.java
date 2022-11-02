package com.example.renwushu.module.sys.entity.dto;

import lombok.Data;

@Data
public class LoginParam {
    String loginname;
    String password;
    String token;
    String captcha;
}
