package jfun;

import jfun.service.CacheKeyGenerator;
import jfun.service.impl.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: miv
 * @Date: 2019-05-06 18:08
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@SpringBootApplication
public class DistributedLockApp {
    public static void main(String[] args) {

        SpringApplication.run(DistributedLockApp.class,args);

    }
    @Bean
    public CacheKeyGenerator cacheKeyGenerator() {
        return new LockKeyGenerator();
    }
}
