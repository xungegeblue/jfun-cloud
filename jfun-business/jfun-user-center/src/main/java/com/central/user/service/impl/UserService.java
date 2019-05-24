package com.central.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.user.dao.UserMapper;
import com.central.common.model.User;
import com.central.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {


    public User getByUsername(String userName) {
        return baseMapper.getByUsername(userName);
    }
}
