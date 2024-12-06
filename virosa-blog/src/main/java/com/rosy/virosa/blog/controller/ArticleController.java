package com.rosy.virosa.blog.controller;

import com.rosy.virosa.common.domain.entity.Article;
import com.rosy.virosa.common.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
