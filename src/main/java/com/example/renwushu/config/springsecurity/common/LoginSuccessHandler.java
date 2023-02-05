package com.example.renwushu.config.springsecurity.common;

import cn.hutool.json.JSONUtil;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import com.example.renwushu.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 登录验证通过进入
* */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtil jwtUtil;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        ServletOutputStream outputStream = response.getOutputStream();

        // 生成jwt返回
        String token = jwtUtil.generateToken(authentication.getName());
        response.setHeader(jwtUtil.getHeader(), token);

        outputStream.write(JSONUtil.toJsonStr(AjaxJson.returnInfo(StatusCode.USER_LOGIN_SUCESS,true)).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}