package com.central.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.central.common.dto.MenuDTO;
import com.central.common.model.Menu;
import com.central.common.model.Role;
import com.central.common.model.User;
import com.central.user.vo.MenuTreeItem;
import com.central.user.vo.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: miv
 * @Date: 2019-05-23 07:36
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
public interface IMenuService {
    /**
     * 根据角色代码获取菜单
     * @param roleCodes
     * @return
     */
    List<MenuDTO> findByRoleCodes(Set<String> roleCodes);

    /**
     * 查询角色列表
     * @param page
     * @param resource
     * @return
     */
    IPage<Role> selectMenus(Page page, Role resource);


    /**
     * 删除菜单
     * @param id
     * @return
     */
    int del(Long id);

    /**
     * 构造用户菜单
     * @param user
     * @return
     */
    Object buildUserMenus(User user);

    /**
     * 构造用户菜单树
     * @param menus
     * @return
     */
    List<MenuTreeItem> buildMenuTree(List<Menu> menus);
}
