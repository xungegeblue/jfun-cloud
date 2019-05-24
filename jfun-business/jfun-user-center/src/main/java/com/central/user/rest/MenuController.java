package com.central.user.rest;

import cn.hutool.core.convert.Convert;
import com.central.common.dto.MenuDTO;
import com.central.common.model.Menu;
import com.central.common.util.Trans2Entity;
import com.central.common.vo.MenuVo;
import com.central.user.service.impl.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: miv
 * @Date: 2019-05-23 16:17
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    Trans2Entity trans2Entity;
    //查询角色菜单
    @GetMapping("/{roleCodes}")
    public List<MenuVo> findMenuByRoles(@PathVariable String roleCodes){
        Set<String> roleSet = (Set<String>) Convert.toCollection(HashSet.class, String.class, roleCodes);
        List<MenuDTO> data = menuService.findByRoleCodes(roleSet);
        List<MenuDTO> menuDTOTree = (List<MenuDTO>) trans2Entity.buildTree(data).get("content");
        return trans2Entity.buildMenus(menuDTOTree);
    }
}
