package com.central.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
/**
 * @author: miv
 * @Date: 2019-06-02 19:49
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
@TableName(value = "sys_permission")
public class Permission implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;//主键.


    private String name;//名称.

    private String alias;//中文名称
    private Long pid; //父编号

    private String url;
    private String method;

    @TableField(value = "create_time")
    private Timestamp createTime;

    @TableField(exist = false)
    private List<Permission> children;

}
