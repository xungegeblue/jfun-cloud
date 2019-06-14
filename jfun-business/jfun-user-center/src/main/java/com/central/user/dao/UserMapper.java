package com.central.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column = "id", property = "roles", many = @Many(select = "com.central.user.dao.RoleMapper.findRoleListByUid"))
    })
    @Select("select * from sys_user where name=#{name}")
    public User getByUsername(@Param("name") String name);


    IPage<User> selectUserPage(Page<User> page, @Param(value = "user") User user);

    @Delete("delete from sys_user_role where uid=#{uid}")
    void delUserRoleByUid(@Param("uid") Long uid);


    void andUserRole(@Param("uid") Long id,@Param("roleIds") List<Long> roleIds);
}
