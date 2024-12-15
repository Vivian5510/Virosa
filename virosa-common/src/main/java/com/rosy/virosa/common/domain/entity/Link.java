package com.rosy.virosa.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 友链(Link)表实体类
 *
 * @author rosy
 * @since 2024-12-09 13:45:31
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("vr_link")
public class Link {
    @TableId
    private Long id;
    private String name;
    private String logo;
    private String description;
    // 网站地址
    private String address;
    // 审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)
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
    // 乐观锁版本
    @Version
    private Long version;
}

