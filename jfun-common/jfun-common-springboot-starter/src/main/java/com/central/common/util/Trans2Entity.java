package com.central.common.util;


import com.central.common.dto.MenuDTO;
import com.central.common.model.Menu;
import com.central.common.vo.MenuMetaVo;
import com.central.common.vo.MenuVo;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Author 谢镜勋
 * @Date 2019/3/16
 */
@Component
public class Trans2Entity {
    public static MenuDTO toDto(Menu arg0) {
        if (arg0 == null) {
            return null;
        }

        MenuDTO menuDTO = new MenuDTO();

        menuDTO.setId(arg0.getId());
        menuDTO.setName(arg0.getName());
        menuDTO.setSort(arg0.getSort());
        menuDTO.setPath(arg0.getPath());
        menuDTO.setComponent(arg0.getComponent());
        menuDTO.setPid(arg0.getPid());
        menuDTO.setIFrame(arg0.getIFrame());
        menuDTO.setIcon(arg0.getIcon());
        return menuDTO;
    }

    public  Map buildTree(List<MenuDTO> menuDtos) {
        List<MenuDTO> trees = new ArrayList<MenuDTO>();

        for (MenuDTO menuDTO : menuDtos) {

            if ("0".equals(menuDTO.getPid().toString())) {
                trees.add(menuDTO);
            }

            for (MenuDTO it : menuDtos) {
                if (it.getPid().equals(menuDTO.getId())) {
                    if (menuDTO.getChildren() == null) {
                        menuDTO.setChildren(new ArrayList<MenuDTO>());
                    }
                    menuDTO.getChildren().add(it);
                }
            }
        }
        Map map = new HashMap(16);
        map.put("content",trees.size() == 0?menuDtos:trees);
        map.put("totalElements",menuDtos!=null?menuDtos.size():0);
        return map;
    }

    public List<MenuVo> buildMenus(List<MenuDTO> menuDtos) {
        List<MenuVo> list = new LinkedList<>();
        menuDtos.forEach(menuDTO -> {
                    if (menuDTO!=null){
                        List<MenuDTO> menuDtoList = menuDTO.getChildren();
                        MenuVo menuVo = new MenuVo();
                        menuVo.setName(menuDTO.getName());
                        menuVo.setPath(menuDTO.getPath());

                        // 如果不是外链
                        if(!menuDTO.getIFrame()){
                            if(menuDTO.getPid().equals(0L)){
                                //一级目录需要加斜杠，不然访问 会跳转404页面
                                menuVo.setPath("/" + menuDTO.getPath());
                                menuVo.setComponent(StringUtils.isEmpty(menuDTO.getComponent())?"Layout":menuDTO.getComponent());
                            }else if(!StringUtils.isEmpty(menuDTO.getComponent())){
                                menuVo.setComponent(menuDTO.getComponent());
                            }
                        }
                        menuVo.setMeta(new MenuMetaVo(menuDTO.getName(),menuDTO.getIcon()));
                        if(menuDtoList!=null && menuDtoList.size()!=0){
                            menuVo.setAlwaysShow(true);
                            menuVo.setRedirect("noredirect");
                            menuVo.setChildren(buildMenus(menuDtoList));
                            // 处理是一级菜单并且没有子菜单的情况
                        } else if(menuDTO.getPid().equals(0L)){
                            MenuVo menuVo1 = new MenuVo();
                            menuVo1.setMeta(menuVo.getMeta());
                            // 非外链
                            if(!menuDTO.getIFrame()){
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
}
