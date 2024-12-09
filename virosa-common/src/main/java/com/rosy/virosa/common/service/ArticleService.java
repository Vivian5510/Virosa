package com.rosy.virosa.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.virosa.common.domain.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {
    List<Integer> selectCategoryIds();

    List<Article> hotArticleList();

    List<Article> getArticleList(Integer pageNo, Integer pageSize, Integer categoryId);

    Article getArticleDetail(Integer id);
}
