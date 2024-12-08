package com.rosy.virosa.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rosy.virosa.common.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author rosy
 * @since 2024-12-08 11:09:51
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}

