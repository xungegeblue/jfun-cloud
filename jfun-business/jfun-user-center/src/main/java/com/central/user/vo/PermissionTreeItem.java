package com.central.user.vo;

import com.central.common.model.Permission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: miv
 * @Date: 2019-06-12 10:23
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
public class PermissionTreeItem implements Serializable {
    private Long pid;
    private Long id;
    private String label;
    List<PermissionTreeItem> children;
    boolean hasChildren;

    public PermissionTreeItem(Long id, String label, Long pid) {
        hasChildren = false;
        this.id = id;
        this.label = label;
        this.pid = pid;
    }
}
