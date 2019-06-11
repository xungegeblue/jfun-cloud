package com.central.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.central.common.model.User;
import com.central.common.vo.LoginAppUser;
import com.central.user.vo.Page;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
public interface IUserService  extends IService<User> {

    public User getByUsername(String userName);

    IPage<User> selectUserPage(Page page, User resource);

    int delete(Long id);

    int update(User resource);

    User create(User resource);

    LoginAppUser getLoginAppUser(User user);
}
