package jfun.controller;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
//    @PreAuthorize("hasAnyRole('ADMIN','USERJOB_ALL','USERJOB_CREATE')")

    @PreAuthorize("hasAnyAuthority('USER_VIEW')")
    @GetMapping("order")
    public Map order() {
        Map map = new HashMap();
        map.put("data","jfun");
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(a);
        map.put("user",a);

        return map;
    }
}
