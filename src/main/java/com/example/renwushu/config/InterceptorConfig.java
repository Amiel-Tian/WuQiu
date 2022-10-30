package com.example.renwushu.config;

import com.example.renwushu.common.json.AjaxJson;
import com.example.renwushu.common.json.StatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InterceptorConfig implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求参数
        Object queryString = request.getQueryString();
        //获取请求头参数
        Object Authorization = request.getHeader("Authorization");
        //获取请求方式
        Object method = request.getMethod();
        //获取请求路径
        Object requestURI = request.getRequestURI();
        //重定向访问路径
//        response.sendRedirect("/bbb2");
//        添加请求头
//        response.addHeader("location","http://baidu.com");
        if (false) {
            //拦截
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().print(AjaxJson.returnExceptionInfo(StatusCode.USER_ILLEGAL_AUTH).toJsonObjec());

            return false;
        } else {
            //放行
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
