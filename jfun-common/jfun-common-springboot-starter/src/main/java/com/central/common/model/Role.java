package com.central.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;
/**
 * @author: miv
 * @Date: 2019-06-02 19:49
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
@TableName("sys_role")
public class Role implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id; // 编号
    private String name; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String remark; // 角色描述,UI界面显示使用

    @TableField(value = "create_time")
    private Timestamp createTime;

    @TableField(exist = false)
    private Set<Permission> permissions;

    @TableField(exist = false)
    private Set<Menu> menus;
}
