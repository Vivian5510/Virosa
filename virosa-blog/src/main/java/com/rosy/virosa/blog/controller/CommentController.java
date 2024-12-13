package com.rosy.virosa.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.SystemException;
import com.rosy.virosa.common.domain.entity.Comment;
import com.rosy.virosa.common.domain.vo.CommentVo;
import com.rosy.virosa.common.domain.vo.SubCommentVo;
import com.rosy.virosa.common.enums.AppHttpStatusEnum;
import com.rosy.virosa.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/articleCommentList")
    public ResponseResult<List<CommentVo>> articleCommentList(Long articleId, Integer pageNo, Integer pageSize) {
        List<CommentVo> commentVos = BeanUtil.copyToList(commentService.getCommentByArticleId(articleId, pageNo, pageSize), CommentVo.class);
        for (CommentVo commentVo : commentVos) {
            commentVo.setPosterName(commentService.getPosterNameById(commentVo.getCreateBy()));
            List<SubCommentVo> subComments = BeanUtil.copyToList(commentService.getSubComments(commentVo.getId()), SubCommentVo.class);
            for (SubCommentVo subCommentVo : subComments) {
                subCommentVo.setPosterName(commentService.getPosterNameById(subCommentVo.getCreateBy()));
                subCommentVo.setToCommentPosterName(commentVo.getPosterName());
            }
            commentVo.setSubComments(subComments);
        }

        return ResponseResult.okResult(commentVos);
    }

    @GetMapping("/linkCommentList")
    public ResponseResult<List<CommentVo>> addComment(Integer pageNo, Integer pageSize) {
        return articleCommentList(-1L, pageNo, pageSize);
    }

    @PostMapping
    public ResponseResult<String> postComment(@RequestBody Comment comment) {
        if (comment.getContent() == null || comment.getContent().isEmpty()) {
            throw new SystemException(AppHttpStatusEnum.PARAM_NOT_NULL);
        }

        commentService.save(comment);

        return ResponseResult.okResult();
    }
}
