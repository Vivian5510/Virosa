package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.mapper.UserMapper;
import com.rosy.virosa.common.service.RoleService;
import com.rosy.virosa.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    RoleService roleService;

    @Override
    public List<String> getPermissionsById(Long id) {
        List<Long> roleIds = baseMapper.getRoleIdsByUserId(id);
        return roleService.getRolesPermissionsByRoleIds(roleIds);
    }
}

