package com.central.user.service;

import com.central.common.dto.MenuDTO;
import com.central.common.model.Menu;

import java.util.List;
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
}
