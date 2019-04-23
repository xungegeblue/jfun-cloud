package jfun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@RestController
public class OrderResource {
    @GetMapping("order")
    public Map order() {
        Map map = new HashMap();
        map.put("data","jfun");
        return map;
    }
}
