package com.central.oauth2.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.code.kaptcha.util.Config;
import java.util.Properties;

/**
 * @Auther: miv
 * @Date: 2019-05-31 17:23
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 验证码配置
 */
@Configuration
public class KaptchaConfig {
    private static final String KAPTCHA_BORDER = "kaptcha.border";
    private static final String KAPTCHA_TEXTPRODUCER_FONT_COLOR = "kaptcha.textproducer.font.color";
    private static final String KAPTCHA_TEXTPRODUCER_CHAR_SPACE = "kaptcha.textproducer.char.space";
    private static final String KAPTCHA_IMAGE_WIDTH = "kaptcha.image.width";
    private static final String KAPTCHA_IMAGE_HEIGHT = "kaptcha.image.height";
    private static final String KAPTCHA_TEXTPRODUCER_CHAR_LENGTH = "kaptcha.textproducer.char.length";
    private static final Object KAPTCHA_IMAGE_FONT_SIZE = "kaptcha.textproducer.font.size";

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();

        properties.put(KAPTCHA_BORDER, "no");
        properties.put(KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        properties.put(KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "50");
        properties.put(KAPTCHA_IMAGE_WIDTH, "100");
        properties.put(KAPTCHA_IMAGE_HEIGHT, "30");
        properties.put(KAPTCHA_IMAGE_FONT_SIZE, "35");
        properties.put(KAPTCHA_TEXTPRODUCER_CHAR_LENGTH,"4");

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
