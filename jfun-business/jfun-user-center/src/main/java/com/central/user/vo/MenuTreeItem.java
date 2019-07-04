package com.central.user.vo;

import lombok.Data;

import java.util.List;

/**
 * @author: miv
 * @Date: 2019-06-12 12:36
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
public class MenuTreeItem {
    private Long pid;
    private Long id;
    private String label;

    public MenuTreeItem(Long id, Long pid, String label) {
        this.id = id;
        this.pid = pid;
        this.label = label;
    }

    List<MenuTreeItem> children;

    boolean hasChildren;
}
