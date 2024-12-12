package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.domain.entity.Menu;
import com.rosy.virosa.common.domain.entity.Role;
import com.rosy.virosa.common.mapper.RoleMapper;
import com.rosy.virosa.common.service.MenuService;
import com.rosy.virosa.common.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表(Role)表服务实现类
 *
 * @author rosy
 * @since 2024-12-12 22:46:45
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    MenuService menuService;

    @Override
    public List<String> getRolesPermissionsByRoleIds(List<Long> roleIds) {
        List<Long> permissionIds = baseMapper.getRolesPermissionIdsByRoleIds(roleIds);
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Menu::getId, permissionIds);
        queryWrapper.select(Menu::getMenuName);
        return menuService.list(queryWrapper)
                .stream()
                .map(Menu::getMenuName)
                .collect(Collectors.toList());
    }
}

