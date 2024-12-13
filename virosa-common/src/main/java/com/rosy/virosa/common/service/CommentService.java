package com.rosy.virosa.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.virosa.common.domain.entity.Comment;

import java.util.List;

/**
 * 评论表(Comment)表服务接口
 *
 * @author rosy
 * @since 2024-12-13 19:37:13
 */
public interface CommentService extends IService<Comment> {

    List<Comment> getCommentByArticleId(Long articleId, Integer pageNo, Integer pageSize);

    String getPosterNameById(Long createBy);

    List<Comment> getSubComments(Long rootId);
}

