package com.rosy.virosa.common.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 评论表(Comment)表实体类
 *
 * @author rosy
 * @since 2024-12-13 19:37:13
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("vr_comment")
public class Comment {
    @TableId
    private Long id;
    // 评论类型（0代表文章评论，1代表友链评论）
    private String type;
    // 文章id
    private Long articleId;
    // 根评论id
    private Long rootId;
    // 评论内容
    private String content;
    // 所回复的目标评论的userid
    private Long toCommentPosterId;
    // 回复目标评论id
    private Long toCommentId;
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

