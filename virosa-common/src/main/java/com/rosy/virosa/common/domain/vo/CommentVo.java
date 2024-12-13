package com.rosy.virosa.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private Long id;
    // 文章id
    private Long articleId;
    // 评论内容
    private String content;
    //发布者id
    private Long createBy;
    private Date createTime;

    //评论者名字
    private String posterName;
    //回复评论
    private List<SubCommentVo> subComments;
}
