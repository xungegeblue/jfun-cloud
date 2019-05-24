package com.central.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: miv
 * @Date: 2019-05-24 11:09
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class Test {

    @GetMapping("/test")
    public ResponseEntity test(){
        return ResponseEntity.ok("test");
    }
}
