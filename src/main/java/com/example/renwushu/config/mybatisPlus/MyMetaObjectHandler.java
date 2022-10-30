package com.example.renwushu.config.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/*
* mybatisPlus设置自动填充时间
* */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    // 自动插入、更新
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill...");
        this.setFieldValByName("createDate", new Date(), metaObject);
        this.setFieldValByName("updateDate", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill...");
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
    }
}