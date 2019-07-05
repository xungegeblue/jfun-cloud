package com.central.oauth2.rest;

import com.central.common.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: miv
 * @Date: 2019-06-03 23:39
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class LoginController {

    @Resource
    private ConsumerTokenServices consumerTokenServices;

    /**
     * 退出登陆
     *
     * @param accessToken
     * @return
     */
    @GetMapping("/logout")
    public Result logout(@RequestParam("token") String accessToken) {
        if (consumerTokenServices.revokeToken(accessToken)) {
            return Result.succeed("退出系统");
        } else {
            return Result.failed("退出失败");
        }
    }

}
