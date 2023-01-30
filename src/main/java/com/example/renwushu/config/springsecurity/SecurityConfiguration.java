package com.example.renwushu.config.springsecurity;

import com.example.renwushu.config.springsecurity.common.*;
import com.example.renwushu.config.springsecurity.jtw.CaptchaFilter;
import com.example.renwushu.config.springsecurity.jtw.JwtAuthenticationFilter;
import com.example.renwushu.config.springsecurity.service.UserService;
import com.example.renwushu.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;


/*
 * Security配置
 * */
@Configuration
@EnableWebSecurity    // 添加 security 过滤器
@EnableGlobalMethodSecurity(prePostEnabled = true)    // 启用方法级别的权限认证
public class SecurityConfiguration {
    @Resource
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private  CaptchaFilter captchaFilter;
    @Resource
    private JwtLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Resource
    private UserService userService;
    @Resource
    private JwtUtil jwtUtil;
    //白名单
    public static final String[] URL_WHITELIST = {
            "/swagger-resources",
            "/swagger-ui/**",
            "/v3/**",
            "/**.html",
            "/webjars/**",
            "/favicon.ico",
            "/captcha",
            "/login",
            "/logout",
            "/websocket/**",
    };

    /*
     * 配置 HttpSecurity
     * */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 基于 token，不需要 csrf
                .csrf().disable()
                // 基于 token，不需要 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //登录成功，失败
                .and().formLogin().successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)

                //退出
                .and().logout().logoutSuccessHandler(jwtLogoutSuccessHandler)

                // 设置权限
                .and().authorizeRequests()
                // 请求放开
                .antMatchers(URL_WHITELIST).permitAll()
                // 其他地址的访问均需验证权限
                .anyRequest().authenticated()

                //异常处理器 处理authenticationEntryPoint认证失败、accessDeniedHandler鉴权失败
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler).and()

                // 添加 JWT 过滤器，除登录请求其他请求进入判断是否为有权限用户
                .addFilter(new JwtAuthenticationFilter(authenticationManager,jwtUtil))
                // 添加 JWT 过滤器，JWT 过滤器在用户名密码认证过滤器之前。判断验证码
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                // 认证用户时用户信息加载配置，注入springAuthUserService
                .userDetailsService(userService)
                .build();
    }


    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    /**
     * 密码明文加密方式配置（使用国密SM4）
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /*
        * BCryptPasswordEncoder
        * 密码加密
        * String pass = bCryptPasswordEncoder.encode("111111");
        * 密码验证
        * boolean matches = bCryptPasswordEncoder.matches("111111", pass);
        * */
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置跨源访问(CORS)
     *
     * @return
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}