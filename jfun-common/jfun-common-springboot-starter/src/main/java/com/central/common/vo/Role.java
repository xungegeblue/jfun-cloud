package com.central.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @Auther: miv
 * @Date: 2019-05-20 01:36
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
public class Role {
    String name;
    List<Permission> permissions;
}
