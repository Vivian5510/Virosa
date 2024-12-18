package com.rosy.virosa.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.virosa.common.domain.entity.Menu;
import com.rosy.virosa.common.domain.entity.Role;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author rosy
 * @since 2024-12-12 22:46:45
 */
public interface RoleService extends IService<Role> {
    List<Menu> getRolesPermissionsByRoleIds(List<Long> roleIds);
}

