package com.example.renwushu.config.springsecurity.common;

import cn.hutool.json.JSONUtil;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import com.sun.xml.txw2.output.ResultFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 登录验证失败进入
 * */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write(JSONUtil.toJsonStr(AjaxJson.returnExceptionInfo(StatusCode.FAIL.statusCode, exception.getMessage())).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }
}
