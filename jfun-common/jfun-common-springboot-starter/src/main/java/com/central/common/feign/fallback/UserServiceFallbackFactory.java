package com.central.common.feign.fallback;

import com.central.common.vo.LoginAppUser;
import feign.hystrix.FallbackFactory;
import com.central.common.feign.AppUserService;
import com.central.common.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: miv
 * @Date: 2019-05-19 15:26
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<AppUserService> {

    @Override
    public AppUserService create(final Throwable throwable) {
        return new AppUserService(){
            @Override
            public LoginAppUser findByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new LoginAppUser();
            }
        };
    }
}
