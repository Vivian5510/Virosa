package com.rosy.virosa.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCommentVo {
    private Long id;
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
    //发布者id
    private Long createBy;
    private Date createTime;

    //评论者名字
    private String posterName;
    //回复者名字
    private String toCommentPosterName;
}
