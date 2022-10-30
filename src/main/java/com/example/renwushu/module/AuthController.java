package com.example.renwushu.module;

import com.example.renwushu.common.base.BaseController;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import com.example.renwushu.config.springsecurity.service.UserService;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.entity.dto.LoginParam;
import com.example.renwushu.utils.JwtUtil;
import com.example.renwushu.utils.KaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
public class AuthController extends BaseController {
    @Autowired
    private KaptchaUtil kaptchaUtil;

    /**
     * 图片验证码
     */
    @ResponseBody
    @GetMapping("/captcha")
    public AjaxJson captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AjaxJson ajaxJson = new AjaxJson<>();

        Map captcha = kaptchaUtil.getCaptcha();

        ajaxJson.setData(captcha);
        return ajaxJson;
    }

    @ResponseBody
    @PostMapping("/login")
    public void login(LoginParam param){
        /*springSecurity框架自带login，此处仅为swagger显示使用*/
    }

    @GetMapping("/logout")
    public void logout() {
        /*springSecurity框架自带退出登录login，此处仅为swagger显示使用*/
    }

    @ResponseBody
    @GetMapping("/list")
    public AjaxJson list(){
        AjaxJson ajaxJson = new AjaxJson();

        ajaxJson.setData("成功访问");

        return ajaxJson;
    }
}