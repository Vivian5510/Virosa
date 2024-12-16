package com.rosy.virosa.blog.runnner;

import com.rosy.virosa.common.service.ArticleService;
import com.rosy.virosa.utilis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RedisViewCountRunner implements CommandLineRunner {
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    ArticleService articleService;

    @Override
    public void run(String... args) throws Exception {
        Map<String, Long> ViewCountMap = new HashMap<>();
        articleService.list().forEach(article -> ViewCountMap.put(article.getId().toString(), article.getViewCount()));
        redisUtils.setCacheMap("article:viewCount", ViewCountMap);
    }
}
