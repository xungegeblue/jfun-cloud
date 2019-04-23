package jfun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jfun.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    //查询角色的所有权限
    @Select("SELECT p.* from sys_role_permission up LEFT JOIN sys_permission p ON(up.permission_id = p.id) WHERE up.role_id = #{rid} ")
    Set<Permission> findByRoleId(@Param("rid")long rid);
}
