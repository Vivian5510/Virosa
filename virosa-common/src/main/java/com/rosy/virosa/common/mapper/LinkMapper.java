package com.rosy.virosa.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rosy.virosa.common.domain.entity.Link;
import org.apache.ibatis.annotations.Mapper;

/**
 * 友链(Link)表数据库访问层
 *
 * @author rosy
 * @since 2024-12-09 13:45:31
 */
@Mapper
public interface LinkMapper extends BaseMapper<Link> {

}

