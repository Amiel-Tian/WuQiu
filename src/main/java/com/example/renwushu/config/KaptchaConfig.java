package com.example.renwushu.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/*
* Kaptcha验证码的生成规则配置
* */
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        /*验证码图片高度像素*/
        properties.put("kaptcha.image.height", "40");
        properties.put("kaptcha.image.width", "120");
        /*验证码图片不生成边框*/
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.border.color", "black");
        properties.put("kaptcha.border.thickness", "1");
        /*验证码图片字体颜色*/
        properties.put("kaptcha.textproducer.font.color", "black");
        /*验证码图片文字字体大小*/
        properties.put("kaptcha.textproducer.font.size", "30");
        /*验证码背景颜色（开始颜色——结束颜色）*/
        properties.put("kaptcha.background-color.from", "lightGray");
        properties.put("kaptcha.background-color.to", "white");
        /*验证码内容间隔*/
        properties.put("kaptcha.textproducer.char.space", "4");
        /*验证码内容源*/
        properties.put("kaptcha.content.source", "ABCDEFGHIJKLMNOPQRSTUVWXYZ2345678923456789");
        /*验证码长度*/
        properties.put("kaptcha.content.length", "4");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}