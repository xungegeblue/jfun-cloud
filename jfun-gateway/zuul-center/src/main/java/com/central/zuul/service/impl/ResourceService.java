package com.central.zuul.service.impl;

import com.central.common.constant.ServiceNameContant;
import com.central.common.vo.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

/**
 * @Auther: miv
 * @Date: 2019-06-21 16:28
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@FeignClient(name = ServiceNameContant.USER_CENTER)
public interface ResourceService {

    //根据用户角色获取资源
    @GetMapping("//resource/{username}")
    public Set<Resource> findAll(@PathVariable("username") String username);
}
