package jfun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jfun.dao.RoleMapper;
import jfun.entity.Role;
import jfun.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> implements IRoleService {


    @Override
    public Set<Role> findRoleByUid(Long uid) {
        return baseMapper.findRoleByUid(uid);
    }
}
