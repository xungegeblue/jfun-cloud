package com.central.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author: miv
 * @Date: 2019-05-23 07:37
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
@TableName(value = "sys_menu")
public class Menu implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private Long sort;
    private String path;
    private String component;
    private String icon;
    /**
     * 上级菜单ID
     */
    private Long pid;
    /**
     * 是否为外链 true/false
     */
    private Boolean iFrame;

    @TableField(exist = false)
    private List<Menu> children;
}
