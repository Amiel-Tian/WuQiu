package com.example.renwushu;

import com.example.renwushu.config.springsecurity.SecurityConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScan("com.example.renwushu.**.dao")
//@SpringBootApplication(exclude = SecurityConfiguration.class)关闭Security默认登录
public class RenwushuApplication {

    public static void main(String[] args) {
        SpringApplication.run(RenwushuApplication.class, args);
    }

}
