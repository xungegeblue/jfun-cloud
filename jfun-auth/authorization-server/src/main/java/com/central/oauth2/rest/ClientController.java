package com.central.oauth2.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: miv
 * @Date: 2019-05-31 17:14
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description: 管理客户端信息
 */
@RestController
public class ClientController {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }
}
