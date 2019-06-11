package com.central.gateway.feign;

import com.central.common.constant.ServiceNameContant;
import com.central.common.model.Menu;
import com.central.common.vo.MenuVo;
import com.central.gateway.feign.fallback.MenuServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Auther: miv
 * @Date: 2019-05-23 07:27
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@FeignClient(name = ServiceNameContant.USER_CENTER, fallbackFactory = MenuServiceFallbackFactory.class, decode404 = true)
public interface MenuService {

    //弃用
    @GetMapping(value = "/menu/{roleCodes}")
    List<MenuVo> findByRoleCodes(@PathVariable("roleCodes") String roleCodes);
}
