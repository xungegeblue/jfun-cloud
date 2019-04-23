package jfun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jfun.entity.Permission;

import java.util.List;
import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
public interface IPermissionService extends IService<Permission> {
    Set<Permission> findByRoleId(long rid);
}
