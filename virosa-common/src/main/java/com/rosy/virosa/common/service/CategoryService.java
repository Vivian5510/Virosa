package com.rosy.virosa.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.virosa.common.domain.entity.Category;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author rosy
 * @since 2024-12-08 11:09:51
 */
public interface CategoryService extends IService<Category> {

    List<Category> getCategorylist();
}

