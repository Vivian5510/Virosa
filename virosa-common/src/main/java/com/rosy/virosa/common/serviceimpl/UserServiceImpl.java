package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.domain.entity.Menu;
import com.rosy.virosa.common.domain.entity.Role;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.mapper.UserMapper;
import com.rosy.virosa.common.service.RoleService;
import com.rosy.virosa.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author rosy
 * @since 2024-12-12 22:26:02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    @Lazy
    RoleService roleService;

    @Override
    public List<Menu> getMenusById(Long id) {
        List<Long> roleIds = baseMapper.getRoleIdsByUserId(id);
        return roleService.getRolesPermissionsByRoleIds(roleIds);
    }

    @Override
    public boolean isUserNameExisted(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, userName);
        User user = getOne(queryWrapper);
        return user != null;
    }

    @Override
    public boolean isEmailExisted(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        User user = getOne(queryWrapper);
        return user != null;
    }

    @Override
    public List<Role> getUserRolesById(Long id) {
        List<Long> roleIds = baseMapper.getRoleIdsByUserId(id);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Role::getId, roleIds);
        return roleService.list(queryWrapper);
    }
}

