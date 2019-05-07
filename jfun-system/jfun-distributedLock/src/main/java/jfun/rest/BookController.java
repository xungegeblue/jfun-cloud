package jfun.rest;

import jfun.ano.CacheLock;
import jfun.ano.CacheParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: miv
 * @Date: 2019-05-06 18:07
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
@RequestMapping("/books")
public class BookController {
    @CacheLock(prefix = "books")
    @GetMapping
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }
}
