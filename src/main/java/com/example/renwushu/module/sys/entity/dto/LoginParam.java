package com.example.renwushu.module.sys.entity.dto;

import lombok.Data;

@Data
public class LoginParam {
    String username;
    String password;
    String token;
    String captcha;
}
