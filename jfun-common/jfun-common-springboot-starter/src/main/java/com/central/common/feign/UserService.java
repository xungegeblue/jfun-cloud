package com.central.common.feign;

import com.central.common.constant.ServiceNameContant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: miv
 * @Date: 2019-05-19 13:47
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@FeignClient(name = ServiceNameContant.USER_CENTER)
public interface UserService {

    @GetMapping("user")
    public Object findByUsername(@RequestParam("username") String username);
}
