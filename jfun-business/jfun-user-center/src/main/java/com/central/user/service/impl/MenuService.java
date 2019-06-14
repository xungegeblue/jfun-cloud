package com.central.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.common.annotation.LoginUser;
import com.central.common.dto.MenuDTO;
import com.central.common.model.Menu;
import com.central.common.model.Permission;
import com.central.common.model.Role;
import com.central.common.model.User;
import com.central.common.util.Trans2Entity;
import com.central.common.vo.MenuMetaVo;
import com.central.common.vo.MenuVo;
import com.central.user.dao.MenuMapper;
import com.central.user.dao.RoleMapper;
import com.central.user.dao.UserMapper;
import com.central.user.service.IMenuService;
import com.central.user.vo.MenuTreeItem;
import com.central.user.vo.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Auther: miv
 * @Date: 2019-05-23 07:38
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Slf4j
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<MenuDTO> findByRoleCodes(Set<String> roleCodes) {
        List<Menu> menus = baseMapper.findByRoleCodes(roleCodes);
        List<MenuDTO> collect = menus.stream().map(Trans2Entity::toDto).collect(Collectors.toList());
        return collect;
    }


    @Override
    public IPage<Role> selectMenus(Page page, Role resource) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(!StringUtils.isEmpty(resource.getName()), "label", resource.getName());
        return buildTree(baseMapper.selectPage(page, wrapper));
    }


    @Override
    public int del(Long id) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id)
                .or().eq("pid", id);
        return baseMapper.delete(wrapper);
    }

    @Autowired
    public UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Object buildUserMenus(User user) {
        List<Role> menuDTOList = user.getRoles();
        List<MenuDTO> menus = getMenuByRole(menuDTOList);
        List<MenuDTO> menuDTOTree = (List<MenuDTO>) buildTree(menus).get("content");
        return buildMenus(menuDTOTree);
    }

    @Override
    public List<MenuTreeItem> buildMenuTree(List<Menu> menus) {
        List<MenuTreeItem> data = menus.stream().map
                (menu -> new MenuTreeItem(menu.getId(), menu.getPid(), menu.getName())
                ).collect(Collectors.toList());
        List<MenuTreeItem> tree = new ArrayList<>();
        for (MenuTreeItem m : data) {
            if (m.getPid().equals(0L)) {
                tree.add(m);
            } else {
                for (MenuTreeItem item : data) {
                    if (m.getPid().equals(item.getId())) {
                        if (item.getChildren() == null) {
                            item.setChildren(new ArrayList<>());
                            item.setHasChildren(true);
                        }
                        item.getChildren().add(m);
                    }
                }
            }
        }
        return tree;
    }

    private List<MenuDTO> getMenuByRole(List<Role> roles) {
        Set<Menu> menus = new LinkedHashSet<>();
        for (Role role : roles) {
            List<Menu> rs = baseMapper.getMenuByRole(role.getId());
            menus.addAll(rs);
        }
        List<MenuDTO> collect = menus.stream().map(Trans2Entity::toDto).collect(Collectors.toList());
        return collect;
    }

    public List<MenuVo> buildMenus(List<MenuDTO> menuDTOS) {
        List<MenuVo> list = new LinkedList<>();
        menuDTOS.forEach(menuDTO -> {
                    if (menuDTO != null) {
                        List<MenuDTO> menuDTOList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(menuDTO.getName());
                        menuVo.setPath(menuDTO.getPath());

                        // 如果不是外链
                        if (!menuDTO.getIFrame()) {
                            if (menuDTO.getPid().equals(0L)) {
                                //一级目录需要加斜杠，不然访问 会跳转404页面
                                menuVo.setPath("/" + menuDTO.getPath());
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent()) ? "Layout" : menuDTO.getComponent());
                            } else if (!StringUtils.isEmpty(menuDTO.getComponent())) {
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getName(), menuDTO.getIcon()));
                        if (menuDTOList != null && menuDTOList.size() != 0) {
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDTOList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if (menuDTO.getPid().equals(0L)) {
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if (!menuDTO.getIFrame()) {
                                menuVo1.setPath("index");
                                menuVo1.setName(menuVo.getName());
                                menuVo1.setComponent(menuVo.getComponent());
                            } else {
                                menuVo1.setPath(menuDTO.getPath());
                            }
                            menuVo.setName(null);
                            menuVo.setMeta(null);
                            menuVo.setComponent("Layout");
                            List<MenuVo> list1 = new ArrayList<MenuVo>();
                            list1.add(menuVo1);
                            menuVo.setChildren(list1);
                        }
                        list.add(menuVo);
                    }
                }
        );
        return list;
    }

    public Map buildTree(List<MenuDTO> menuDTOS) {
        //排序
        menuDTOS = menuDTOS.stream().sorted(new Comparator<MenuDTO>() {
            @Override
            public int compare(MenuDTO o1, MenuDTO o2) {
                return o1.getSort().intValue() - o2.getSort().intValue();
            }
        }).collect(Collectors.toList());

        List<MenuDTO> trees = new ArrayList<MenuDTO>();

        for (MenuDTO menuDTO : menuDTOS) {

            if ("0".equals(menuDTO.getPid().toString())) {
                trees.add(menuDTO);
            }

            for (MenuDTO it : menuDTOS) {
                if (it.getPid().equals(menuDTO.getId())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<MenuDTO>());
                    }
                    menuDTO.getChildren().add(it);
                }
            }
        }
        Map map = new HashMap();
        map.put("content", trees.size() == 0 ? menuDTOS : trees);
        map.put("totalElements", menuDTOS != null ? menuDTOS.size() : 0);
        return map;
    }

    IPage<Menu> buildTree(IPage<Menu> page) {
        List<Menu> menus = page.getRecords();
        List<Menu> tree = new ArrayList<>();

        for (Menu m : menus) {
            if (m.getPid().equals(0L)) {
                tree.add(m);
            }
            for (Menu it : menus) {
                if (it.getPid().equals(m.getId())) {
                    if (m.getChildren() == null) {
                        m.setChildren(new ArrayList<>());
                    }
                    m.getChildren().add(it);
                }
            }
        }
        page.setRecords(tree);
        page.setSize(tree.size());
        return page;
    }
}
