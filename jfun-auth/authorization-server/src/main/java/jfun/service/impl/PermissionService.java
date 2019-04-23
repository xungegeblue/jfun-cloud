package jfun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jfun.dao.PermissionMapper;
import jfun.entity.Permission;
import jfun.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class PermissionService extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public Set<Permission> findByRoleId(long rid) {
        return baseMapper.findByRoleId(rid);
    }
}
