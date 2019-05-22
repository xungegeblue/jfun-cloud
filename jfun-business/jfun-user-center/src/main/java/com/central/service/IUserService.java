package com.central.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.central.entity.User;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
public interface IUserService  extends IService<User> {

    public User getByUsername(String userName);
}
