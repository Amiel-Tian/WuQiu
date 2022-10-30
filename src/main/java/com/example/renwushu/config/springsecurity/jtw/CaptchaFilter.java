package com.example.renwushu.config.springsecurity.jtw;

import com.example.renwushu.config.springsecurity.common.LoginFailureHandler;
import com.example.renwushu.utils.JwtUtil;
import com.example.renwushu.utils.KaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** * 图片验证码校验过滤器，在登录过滤器前 */
@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter{
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private KaptchaUtil kaptchaUtil;
    @Autowired
    private  LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String url = request.getRequestURI();

        if (url.indexOf("/login") == -1){
            filterChain.doFilter(request, response);
            return;
        }
        //获取token
        String token = request.getParameter("token");
        String captcha = request.getParameter("captcha");

        if (StringUtils.isBlank(captcha)){
            loginFailureHandler.onAuthenticationFailure(request,response,new AccountExpiredException("验证码不能为空"));
            return;
        }

        Boolean code = kaptchaUtil.validate(token, captcha);

        if (!code){
            loginFailureHandler.onAuthenticationFailure(request,response,new AccountExpiredException("验证码不正确"));
            return;
        }

        filterChain.doFilter(request, response);
    }
}
