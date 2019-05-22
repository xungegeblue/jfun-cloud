package com.central.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from sys_user where name=#{name}")
    public User getByUsername(@Param("name") String name);
}
