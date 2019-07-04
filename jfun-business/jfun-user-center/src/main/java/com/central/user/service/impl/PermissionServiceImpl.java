package com.central.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.common.model.Menu;
import com.central.common.vo.Resource;
import com.central.user.dao.PermissionMapper;
import com.central.common.model.Permission;
import com.central.user.service.IPermissionService;
import com.central.user.vo.Page;
import com.central.user.vo.PermissionTreeItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {


    @Override
    public Set<Permission> findByRoleId(long rid) {
        return baseMapper.findByRoleId(rid);
    }

    @Override
    public IPage<Permission> selectPermission(Page page, Permission resource) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(resource.getName()), "label", resource.getName())
                .or().like(StringUtils.isNotEmpty(resource.getAlias()), "alias", resource.getAlias());
        return buildTree(baseMapper.selectPage(page, wrapper));
    }

    @Override
    public int del(Long id) {

        //如果权限有被使用不予删除
        int binds = baseMapper.permissionBinds(id);
        if (binds > 0) {
            throw new IllegalStateException("权限被使用，不可以删除");
        } else {
            QueryWrapper<Permission> wrapper = new QueryWrapper<>();
            wrapper.eq("id", id)
                    .or().eq("pid", id);

            return baseMapper.delete(wrapper);
        }

    }

    @Override
    public List<Permission> buildTree(List<Permission> records) {
        List<Permission> tree = new ArrayList<>();
        for (Permission p : records) {
            if (p.getPid().equals(0L)) {
                tree.add(p);
            } else {
                for (Permission item : records) {
                    if (item.getPid().equals(p.getId())) {
                        if (p.getChildren() == null) {
                            p.setChildren(new ArrayList<>());
                        }
                        p.getChildren().add(item);
                    }
                }
            }
        }
        return tree;
    }

    @Override
    public List<PermissionTreeItem> buildSelectTree(List<PermissionTreeItem> records) {
        List<PermissionTreeItem> tree = new ArrayList<>();
        for (PermissionTreeItem p : records) {
            if (p.getPid().equals(0L)) {
                tree.add(p);
            } else { //p不是第一级别
                for (PermissionTreeItem item : records) {
                    if (p.getPid().equals(item.getId())) {
                        if (item.getChildren() == null) {
                            item.setHasChildren(true);
                            item.setChildren(new ArrayList<>());
                        }

                        item.getChildren().add(p);
                    }
                }
            }
        }
        return tree;
    }




    IPage<Permission> buildTree(IPage<Permission> page) {
        List<Permission> menus = page.getRecords();
        List<Permission> tree = new ArrayList<>();

        for (Permission m : menus) {
            if (m.getPid().equals(0L)) {
                tree.add(m);
            }
            for (Permission it : menus) {
                if (it.getPid().equals(m.getId())) {
                    if (m.getChildren() == null) {
                        m.setChildren(new ArrayList<>());
                    }
                    m.getChildren().add(it);
                }
            }
        }
        page.setRecords(tree);
        page.setTotal(tree.size());
        return page;
    }
}
