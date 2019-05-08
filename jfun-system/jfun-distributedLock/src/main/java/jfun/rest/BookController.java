package jfun.rest;

import jfun.ano.CacheLock;
import jfun.ano.CacheParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
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
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @CacheLock(prefix = "books")
    @GetMapping
    public String query(@CacheParam(name = "token") @RequestParam String token) {
//        stringRedisTemplate.opsForValue().set("cloud","haha");
//        String a = stringRedisTemplate.opsForValue().get("cloud");
        return "success - " + token;

    }
}
