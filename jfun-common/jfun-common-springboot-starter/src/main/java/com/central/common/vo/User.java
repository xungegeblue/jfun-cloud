package com.central.common.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

/**
 * @Auther: miv
 * @Date: 2019-05-19 15:35
 * @Web: www.xiejx.cn
 * @Email: 787824374@qq.com
 * @Description:
 */
@Data
public class User {
    private Long id;

    private String name;//名称（昵称或者真实姓名，不同系统不同定义）
    private String password; //密码;
    private String email;//邮箱
    private String avatar;//头像
    private Timestamp createTime;//创建时间
    private Integer state;//用户状态 --等待验证的用户 , 1:正常状态,2：用户被锁定.

    private Set<Role> roles;// 一个用户具有多个角色
}
