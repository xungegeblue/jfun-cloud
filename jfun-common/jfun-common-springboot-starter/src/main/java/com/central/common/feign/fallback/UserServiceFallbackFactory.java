package com.central.common.feign.fallback;

import feign.hystrix.FallbackFactory;
import com.central.common.feign.UserService;
import com.central.common.model.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @Auther: miv
 * @Date: 2019-05-19 15:26
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Slf4j
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {

    public UserService create(final Throwable throwable) {
        return new UserService(){

            public Object findByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new User();
            }
        };
    }
}
