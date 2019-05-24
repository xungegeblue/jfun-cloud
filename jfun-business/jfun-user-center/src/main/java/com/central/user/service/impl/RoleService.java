package com.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.user.dao.RoleMapper;
import com.central.common.model.Role;
import com.central.user.service.IRoleService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    public Set<Role> findRoleByUid(Long uid) {
        return baseMapper.findRoleByUid(uid);
    }
}
