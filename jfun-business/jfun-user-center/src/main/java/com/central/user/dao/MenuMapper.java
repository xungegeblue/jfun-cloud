package com.central.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.central.common.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @Auther: miv
 * @Date: 2019-05-23 07:39
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findByRoleCodes(@Param("roleCodes") Set<String> roleCodes);

    @Select(" SELECT * FROM sys_menu WHERE sys_menu.id IN ( SELECT menu_id as id FROM sys_roles_menus rm WHERE rm.role_id =#{roleId})")
    public List<Menu> findByRoleId(@Param("roleId") Long roleId);

    List<Menu> getMenuByRole(Long id);
}
