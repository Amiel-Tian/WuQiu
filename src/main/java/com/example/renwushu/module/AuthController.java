package com.example.renwushu.module;

import com.example.renwushu.common.base.BaseController;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import com.example.renwushu.module.sys.entity.SysUser;
import com.example.renwushu.module.sys.entity.dto.LoginParam;
import com.example.renwushu.module.sys.service.SysUserService;
import com.example.renwushu.utils.KaptchaUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Slf4j
@RestController
public class AuthController extends BaseController {
    @Autowired
    private KaptchaUtil kaptchaUtil;
    @Autowired
    private SysUserService sysUserService;

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

    @GetMapping("/getUserInfo")
    public AjaxJson getUserInfo(Principal principal) {
        String username = principal.getName();
        SysUser sysUser = sysUserService.getByUser(new SysUser().setLoginname(username));

        if (sysUser == null){
            return AjaxJson.returnExceptionInfo(StatusCode.USER_UNLOGIN);
        }

        return new AjaxJson().setData(sysUser);
    }
}