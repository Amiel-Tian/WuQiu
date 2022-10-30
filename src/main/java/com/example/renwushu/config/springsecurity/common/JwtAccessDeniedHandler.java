package com.example.renwushu.config.springsecurity.common;

import com.alibaba.fastjson.JSON;
import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description 用户权限不足时反给前端的数据
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Content-Type", "application/json");
        httpServletResponse.getWriter().write(JSON.toJSONString(AjaxJson.returnExceptionInfo(StatusCode.CAS_AUTHENTICATION_FAIL)));
    }

}
