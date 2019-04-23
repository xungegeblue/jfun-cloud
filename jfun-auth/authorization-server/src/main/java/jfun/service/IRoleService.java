package jfun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jfun.entity.Role;
import jfun.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
public interface IRoleService extends IService<Role> {
    Set<Role> findRoleByUid(Long uid);
}
