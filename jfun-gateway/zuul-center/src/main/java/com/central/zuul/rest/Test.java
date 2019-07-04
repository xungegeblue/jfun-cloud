package com.central.zuul.rest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: miv
 * @Date: 2019-05-24 11:09
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class Test {

    @GetMapping("/test")
    public ResponseEntity test(){
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        o =  SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        o = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        o = SecurityContextHolder.getContext().getAuthentication().getDetails();
        org.springframework.cloud.alibaba.sentinel.datasource.RuleType  a;
        return ResponseEntity.ok(o);
    }
    @GetMapping("/ok")
    public ResponseEntity ok(){
        return ResponseEntity.ok("ok");
    }





}
