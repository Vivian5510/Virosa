package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.constant.CategoryStatusConstant;
import com.rosy.virosa.common.domain.entity.Category;
import com.rosy.virosa.common.mapper.CategoryMapper;
import com.rosy.virosa.common.service.ArticleService;
import com.rosy.virosa.common.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分类表(Category)表服务实现类
 *
 * @author rosy
 * @since 2024-12-08 11:09:51
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    ArticleService articleService;

    @Override
    public List<Category> getCategorylist() {
        List<Integer> categoryIds = articleService.selectCategoryIds();
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Category::getId, categoryIds);
        queryWrapper.eq(Category::getStatus, CategoryStatusConstant.CATEGORY_STATUS_ACTIVE);
        return list(queryWrapper);
    }
}

