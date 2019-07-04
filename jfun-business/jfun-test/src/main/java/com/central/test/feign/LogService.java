package com.central.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: miv
 * @Date: 2019-06-14 05:29
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@FeignClient(name = "LOG-CENTER")
public interface LogService {

    /**
     * 测试
     * @return
     */
    @GetMapping("log")
    public String log();
}
