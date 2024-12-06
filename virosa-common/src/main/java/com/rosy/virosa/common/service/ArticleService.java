package com.rosy.virosa.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.virosa.common.domain.entity.Article;

import java.util.List;

public interface ArticleService extends IService<Article> {
    List<Article> hotArticleList();
}
