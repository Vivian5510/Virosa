package com.rosy.virosa.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rosy.virosa.common.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * 标签(Tag)表数据库访问层
 *
 * @author rosy
 * @since 2024-12-16 22:42:41
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}

