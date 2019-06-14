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
 * @Auther: miv
 * @Date: 2019-05-23 07:36
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
public interface IMenuService {
    List<MenuDTO> findByRoleCodes(Set<String> roleCodes);

    IPage<Role> selectMenus(Page page, Role resource);

    int del(Long id);

    Object buildUserMenus(User user);

    List<MenuTreeItem> buildMenuTree(List<Menu> menus);
}
