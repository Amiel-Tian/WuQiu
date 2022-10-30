package com.example.renwushu.utils;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
public class KaptchaUtil {
    @Autowired
    private Producer producer;
    @Autowired
    private RedisUtil redisUtil;

    public Map getCaptcha() throws IOException {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        Base64Encoder encoder = new Base64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        // 存储到redis中
        redisUtil.set(key, code);

        log.info("验证码 --- {}---{}", key, code);
        return MapUtil.builder().put("token", key).put("base64Img", base64Img).build();
    }

    public String getCaptchaImg() throws IOException {
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        Base64Encoder encoder = new Base64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());

        return base64Img;
    }

    public Boolean validate(String key, String code) {
        try {
            if (StringUtils.isBlank(code) || StringUtils.isBlank(key)) {
                return false;
            }
            if (!code.equals(redisUtil.get(key))) {
                return false;
            }
            String redisKey = String.valueOf(redisUtil.get(key));
            if (!redisKey.equals(code)) {
                return false;
            }

            return true;
        } catch (Exception e) {

        } finally {

            // 一次性使用
            redisUtil.deleteObject(key);
        }
        return false;
    }
}
