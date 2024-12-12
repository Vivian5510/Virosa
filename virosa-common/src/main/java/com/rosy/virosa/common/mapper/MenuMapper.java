package com.rosy.virosa.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rosy.virosa.common.domain.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单表(Menu)表数据库访问层
 *
 * @author rosy
 * @since 2024-12-12 22:46:10
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

}

