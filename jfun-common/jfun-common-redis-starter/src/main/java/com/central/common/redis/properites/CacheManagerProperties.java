package com.central.common.redis.properites;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @Auther: miv
 * @Date: 2019-05-31 20:12
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "jfun.cache-manager")
public class CacheManagerProperties {
    private List<CacheConfig> configs;
    @Setter
    @Getter
    public static class CacheConfig {
        /**
         * cache key ,作为缓存空间
         */
        private String key;
        /**
         * 过期时间，sec
         */
        private long second = 60;
    }
}
