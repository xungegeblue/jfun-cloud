package jfun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jfun.entity.User;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
public interface IUserService  extends IService<User> {

    public User getByUsername(String userName);
}
