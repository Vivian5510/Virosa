package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.constant.ArticleStatusConstant;
import com.rosy.virosa.common.domain.entity.Article;
import com.rosy.virosa.common.domain.entity.Category;
import com.rosy.virosa.common.mapper.ArticleMapper;
import com.rosy.virosa.common.service.ArticleService;
import com.rosy.virosa.common.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Override
    public List<Integer> selectCategoryIds() {
        return baseMapper.selectCategoryIds();
    }

    @Override
    public List<Article> hotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        /*必须是正式文章*/
        wrapper.eq(Article::getStatus, ArticleStatusConstant.ARTICLE_STATUS_PUBLISHED);
        /*按浏览量排序*/
        wrapper.orderByDesc(Article::getViewCount);
        /*只显示前10条*/
        Page<Article> page = new Page<>(1, 10);
        page(page, wrapper);

        return page.getRecords();
    }

    @Override
    public List<Article> getArticleList(Integer pageNo, Integer pageSize, Integer categoryId) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        /*必须是正式发布的文章*/
        wrapper.eq(Article::getStatus, ArticleStatusConstant.ARTICLE_STATUS_PUBLISHED);
        /*按是否置顶排序*/
        wrapper.orderByDesc(Article::getIsTop);
        /*如果要分类，就筛选分类*/
        wrapper.eq(categoryId > 0, Article::getCategoryId, categoryId);

        Page<Article> page = new Page<>(pageNo, pageSize);
        page(page, wrapper);

        /*性能优化：批量查询缓存*/
        Map<Long, String> categoryMap = categoryService.list()
                .stream()
                .collect(Collectors.toMap(Category::getId, Category::getName));
        List<Article> articleList = page.getRecords();
        articleList.forEach(article ->
                article.setCategoryName(categoryMap.get(article.getCategoryId()))
        );

        return articleList;
    }
}
