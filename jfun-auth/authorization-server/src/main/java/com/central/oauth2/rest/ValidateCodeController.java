package com.central.oauth2.rest;

import cn.hutool.core.lang.Assert;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @Auther: miv
 * @Date: 2019-05-31 17:14
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 验证码模块
 */
@RestController
@Slf4j
public class ValidateCodeController {

    @Autowired
    private Producer producer;


    @GetMapping(value = "/validateCode/{$deviceId}")
    public void createValidateCode(@PathVariable String deviceId, HttpServletResponse response) throws Exception {
        Assert.notNull(deviceId);

        String text = producer.createText();
        BufferedImage image = producer.createImage(text);

        log.info("生成验证码:{}"+text);

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }
}
