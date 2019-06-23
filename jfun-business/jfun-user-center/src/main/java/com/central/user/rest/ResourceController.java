package com.central.user.rest;

import com.central.common.model.User;
import com.central.common.vo.Resource;
import com.central.user.service.IPermissionService;
import com.central.user.service.IUserService;
import com.central.user.service.impl.PermissionService;
import com.central.user.service.impl.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: miv
 * @Date: 2019-06-21 18:20
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
public class ResourceController {

    @Autowired
    IUserService iUserService;

    @GetMapping("/resource/{username}")
    public Set<Resource> findResourceByUser(@PathVariable("username") String username) {
        User user = iUserService.getByUsername(username);
        Set<Resource> resources = user.getRoles()
                .stream()
                .map(r -> r.getPermissions())
                .flatMap(Collection::stream)
                .map(s -> {
                    Resource resource = new Resource();
                    resource.setUrl(s.getUrl());
                    resource.setMethod(s.getMethod());
                    resource.setName(s.getName());
                    return resource;
                }).collect(Collectors.toSet());
        return resources;
    }

}
