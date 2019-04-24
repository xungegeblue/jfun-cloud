package jfun;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author 谢镜勋
 * @Date 2019/4/24
 */
@NacosPropertySource(dataId = "nacos-server", autoRefreshed = true)
@SpringBootApplication
public class NacosServerApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosServerApp.class, args);
    }
}
