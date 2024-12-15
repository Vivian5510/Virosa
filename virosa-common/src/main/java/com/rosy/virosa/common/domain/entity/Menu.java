package com.rosy.virosa.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 菜单表(Menu)表实体类
 *
 * @author rosy
 * @since 2024-12-12 22:46:10
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu")
public class Menu {
    @TableId
    private Long id;
    // 菜单名
    private String menuName;
    // 路由地址
    private String path;
    // 组件路径
    private String component;
    // 菜单状态（0显示 1隐藏）
    private String visible;
    // 菜单状态（0正常 1停用）
    private String status;
    // 权限标识
    private String perms;
    // 菜单图标
    private String icon;
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

