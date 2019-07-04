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
    /**
     * 获取用户信息
     * @param userName
     * @return
     */
    public User getByUsername(String userName);

    /**
     * 获取用户列表
     * @param page
     * @param resource
     * @return
     */
    IPage<User> selectUserPage(Page page, User resource);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 更新用户
     * @param resource
     * @return
     */
    int update(User resource);

    /**
     * 创建用户
     * @param resource
     * @return
     */
    User create(User resource);

    /**
     * 获取登陆用户
     * @param user
     * @return
     */
    LoginAppUser getLoginAppUser(User user);
}
