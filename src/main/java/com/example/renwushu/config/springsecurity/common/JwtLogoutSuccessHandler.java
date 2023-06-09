package com.example.renwushu.config.springsecurity.common;

import cn.hutool.json.JSONUtil;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import com.example.renwushu.utils.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json");
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader(jwtUtil.getHeader(), "");

        outputStream.write(JSONUtil.toJsonStr(AjaxJson.returnInfo(StatusCode.USER_LOGINOUT_SUCESS, true)).getBytes("UTF-8"));

        outputStream.flush();
        outputStream.close();
    }
}
