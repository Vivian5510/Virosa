package com.rosy.virosa.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色表(Role)表实体类
 *
 * @author rosy
 * @since 2024-12-12 22:46:45
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role")
public class Role {
    @TableId
    private Long id;
    private String name;
    // 角色权限字符串
    private String roleKey;
    // 角色状态（0正常 1停用）
    private String status;
    // del_flag
    private Integer delFlag;
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    // 删除标志（0代表未删除，1代表已删除）
    @TableLogic
    private Integer deleted;
    // 乐观锁版本
    @Version
    private Integer version;
}

