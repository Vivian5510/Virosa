package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.domain.entity.Comment;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.mapper.CommentMapper;
import com.rosy.virosa.common.service.CommentService;
import com.rosy.virosa.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author rosy
 * @since 2024-12-13 19:37:13
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    @Lazy
    UserService userService;

    @Override
    public List<Comment> getCommentByArticleId(Long articleId, Integer pageNo, Integer pageSize) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, articleId);
        queryWrapper.eq(Comment::getRootId, -1);
        Page<Comment> page = new Page<>();
        page.setSize(pageSize);
        page.setCurrent(pageNo);
        return list(page, queryWrapper);
    }

    @Override
    public String getPosterNameById(Long createBy) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, createBy);
        queryWrapper.select(User::getUserName);
        return userService.getOne(queryWrapper).getUserName();
    }

    @Override
    public List<Comment> getSubComments(Long rootId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getRootId, rootId);
        return list(queryWrapper);
    }
}

