package com.rosy.virosa.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rosy.virosa.common.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色表(Role)表数据库访问层
 *
 * @author rosy
 * @since 2024-12-12 22:46:45
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Long> getRolesPermissionIdsByRoleIds(List<Long> roleIds);
}

