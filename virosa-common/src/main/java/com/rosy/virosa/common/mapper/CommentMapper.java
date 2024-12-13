package com.rosy.virosa.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rosy.virosa.common.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author rosy
 * @since 2024-12-13 19:37:13
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}

