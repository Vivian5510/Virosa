package com.rosy.virosa.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.entity.Article;
import com.rosy.virosa.common.domain.vo.ArticleDetailVo;
import com.rosy.virosa.common.domain.vo.ArticleIntroVo;
import com.rosy.virosa.common.domain.vo.HotArticleVo;
import com.rosy.virosa.common.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/test")
    public List<Article> TestService() {
        return articleService.list();
    }

    @RequestMapping("/hotArticle")
    public ResponseResult<List<HotArticleVo>> getHotArticleList() {
        List<HotArticleVo> res = BeanUtil.copyToList(articleService.hotArticleList(), HotArticleVo.class);
        return ResponseResult.okResult(res);
    }

    @GetMapping("/articleList")
    public ResponseResult<List<ArticleIntroVo>> getArticlePage(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId) {

        List<ArticleIntroVo> res = BeanUtil.copyToList(articleService.getArticleList(pageNo, pageSize, categoryId), ArticleIntroVo.class);
        return ResponseResult.okResult(res);
    }

    @GetMapping("/{id}")
    public ResponseResult<ArticleDetailVo> getArticleDetail(@PathVariable("id") Integer id) {
        ArticleDetailVo res = BeanUtil.copyProperties(articleService.getArticleDetail(id), ArticleDetailVo.class);
        return ResponseResult.okResult(res);
    }
}
