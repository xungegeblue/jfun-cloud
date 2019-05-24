package com.central.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.central.common.model.Role;

import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
public interface IRoleService extends IService<Role> {
    Set<Role> findRoleByUid(Long uid);
}
