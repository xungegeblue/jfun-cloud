package com.central.test.rest;

import com.central.test.feign.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: miv
 * @Date: 2019-06-14 05:35
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class TestController {
    @Autowired
    LogService logService;

    @GetMapping("/test")
    public String test() {
        return logService.log();
    }
    @GetMapping("/test2")
    public void test2(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
    }
}
