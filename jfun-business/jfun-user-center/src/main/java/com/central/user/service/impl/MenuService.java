package com.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.common.dto.MenuDTO;
import com.central.common.model.Menu;
import com.central.common.util.Trans2Entity;
import com.central.user.dao.MenuMapper;
import com.central.user.service.IMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
        List<Menu> menus =  baseMapper.findByRoleCodes(roleCodes);
        List<MenuDTO> collect = menus.stream().map(Trans2Entity::toDto).collect(Collectors.toList());
        return collect;
    }

}
