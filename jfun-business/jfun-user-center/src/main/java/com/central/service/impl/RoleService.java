package com.central.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.dao.RoleMapper;
import com.central.entity.Role;
import com.central.service.IRoleService;
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
