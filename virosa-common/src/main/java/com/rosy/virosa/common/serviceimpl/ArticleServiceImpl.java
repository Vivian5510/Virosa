package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.domain.entity.Article;
import com.rosy.virosa.common.mapper.ArticleMapper;
import com.rosy.virosa.common.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Override
    public List<Article> hotArticleList() {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        /*必须是正式文章*/
        wrapper.eq(Article::getStatus, 0);
        /*按浏览量排序*/
        wrapper.orderByDesc(Article::getViewCount);
        /*只显示前10条*/
        Page<Article> page = new Page<>(1, 10);
        page(page, wrapper);

        return page.getRecords();
    }
}
