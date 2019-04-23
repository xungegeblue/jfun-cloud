package jfun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@RestController
public class OrderResource {
    @GetMapping("order")
    public Object order() {
        return new Object();
    }
}
