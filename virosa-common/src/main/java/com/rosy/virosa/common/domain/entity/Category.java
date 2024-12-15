package com.rosy.virosa.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 分类表(Category)表实体类
 *
 * @author rosy
 * @since 2024-12-08 11:09:51
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("vr_category")
public class Category {
    @TableId
    private Long id;
    // 分类名
    private String name;
    // 父分类id，如果没有父分类为-1
    private Long pid;
    // 描述
    private String description;
    // 状态0:正常,1禁用
    private String status;
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
    @Version
    private Integer version;
}

