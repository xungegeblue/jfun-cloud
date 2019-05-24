package com.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.user.dao.PermissionMapper;
import com.central.common.model.Permission;
import com.central.user.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    public Set<Permission> findByRoleId(long rid) {
        return baseMapper.findByRoleId(rid);
    }
}
