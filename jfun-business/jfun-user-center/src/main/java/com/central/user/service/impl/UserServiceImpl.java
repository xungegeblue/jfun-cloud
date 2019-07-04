package com.central.user.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.central.common.model.Role;
import com.central.common.vo.LoginAppUser;
import com.central.user.dao.UserMapper;
import com.central.common.model.User;
import com.central.user.service.IRoleService;
import com.central.user.service.IUserService;
import com.central.user.vo.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author 谢镜勋
 * @Date 2019/4/23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    IRoleService roleService;

    @Override
    public User getByUsername(String userName) {
        return baseMapper.getByUsername(userName);
    }

    @Override
    public IPage<User> selectUserPage(Page page, User resource) {
        return baseMapper.selectUserPage(page, resource);
    }

    @Override
    public int delete(Long id) {
        return baseMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(User resource) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", resource.getId());
        int i = baseMapper.update(resource, wrapper);
        if (i > 0) {
            relationRole(resource);
        }
        return i;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User create(User resource) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        encoder.encode(resource.getPassword());
        resource.setAvatar("https://i.loli.net/2018/12/06/5c08894d8de21.jpg");
        resource.setCreateTime(DateUtil.date().toTimestamp());

        //合法性校验
        int i = baseMapper.insert(resource);

        if (i > 0) {
            relationRole(resource);
        }

        return resource;
    }

    @Override
    public LoginAppUser getLoginAppUser(User user) {
        if (user == null) {
            return null;
        }
        Set<String> au = user.getRoles().stream()
                .map(role -> role.getPermissions())
                .flatMap(Collection::stream)
                .map(permission -> permission.getName())
                .collect(Collectors.toSet());
        LoginAppUser loginAppUser = new LoginAppUser();

        BeanUtils.copyProperties(user, loginAppUser);

        loginAppUser.setPermissions(au);
        return loginAppUser;
    }

    /**
     * 设置角色关系
     * @param resource
     */
    private void relationRole(User resource) {
        List<Long> roleIds = resource.getRoles().stream().map(Role::getId).collect(Collectors.toList());
        baseMapper.delUserRoleByUid(resource.getId());
        baseMapper.andUserRole(resource.getId(), roleIds);
    }
}
