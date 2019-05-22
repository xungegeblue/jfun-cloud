package com.central.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.dao.UserMapper;
import com.central.entity.User;
import com.central.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;


    public User getByUsername(String userName) {
        return userMapper.getByUsername(userName);
    }
}
