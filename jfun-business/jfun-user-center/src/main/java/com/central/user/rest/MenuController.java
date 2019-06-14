package com.central.user.rest;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.central.common.annotation.LoginUser;
import com.central.common.dto.MenuDTO;
import com.central.common.model.Menu;
import com.central.common.model.Role;
import com.central.common.model.User;
import com.central.common.util.Trans2Entity;
import com.central.common.vo.MenuVo;
import com.central.user.service.IUserService;
import com.central.user.service.impl.MenuService;
import com.central.user.vo.MenuTreeItem;
import com.central.user.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: miv
 * @Date: 2019-05-23 16:17
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @Autowired
    Trans2Entity trans2Entity;

    @Autowired
    IUserService userService;

    //查询角色菜单
    @GetMapping("/{roleCodes}")
    public List<MenuVo> findMenuByRoles(@PathVariable String roleCodes) {
        Set<String> roleSet = (Set<String>) Convert.toCollection(HashSet.class, String.class, roleCodes);
        List<MenuDTO> data = menuService.findByRoleCodes(roleSet);
        List<MenuDTO> menuDTOTree = (List<MenuDTO>) trans2Entity.buildTree(data).get("content");
        return trans2Entity.buildMenus(menuDTOTree);
    }

    @GetMapping("build")
    public ResponseEntity buildMenus(@LoginUser(isFull = true) User user) {
        Object menus = menuService.buildUserMenus(user);
        //查询用户关联的菜单
        return ResponseEntity.ok(menus);
    }

    //查询
    @GetMapping
    public ResponseEntity find(Page page, Role resource) {
        IPage<Role> iPage = menuService.selectMenus(page, resource);
        return ResponseEntity.ok(iPage);
    }

    @GetMapping("tree")
    public ResponseEntity tree() {
        List<Menu> menus = menuService.list();
        List<MenuTreeItem> tree = menuService.buildMenuTree(menus);
        return ResponseEntity.ok(tree);
    }


    //删除
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        menuService.del(id);
        return ResponseEntity.ok().build();
    }


    //修改
    @PutMapping
    public ResponseEntity edit(@RequestBody Menu resource) {
        menuService.updateById(resource);
        return ResponseEntity.ok().build();
    }

    //添加
    @PostMapping
    public ResponseEntity create(@Validated @RequestBody Menu resource) {
        menuService.save(resource);
        return ResponseEntity.ok().build();
    }

}
