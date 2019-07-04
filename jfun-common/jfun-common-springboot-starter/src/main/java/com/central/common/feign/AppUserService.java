package com.central.common.feign;

import com.central.common.constant.ServiceNameContant;
import com.central.common.model.User;
import com.central.common.vo.LoginAppUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: miv
 * @Date: 2019-05-19 13:47
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@FeignClient(name = ServiceNameContant.USER_CENTER)
public interface AppUserService {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping("findByUsername")
    public LoginAppUser findByUsername(@RequestParam("username") String username);
}
