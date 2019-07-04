package com.central.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: miv
 * @Date: 2019-06-08 23:52
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:不需要配置在，zuul里面配置就行
 */
public class ConfigurerAdapter implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);

    }
}
