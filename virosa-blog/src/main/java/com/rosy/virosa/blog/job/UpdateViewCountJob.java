package com.rosy.virosa.blog.job;

import com.rosy.virosa.common.domain.entity.Article;
import com.rosy.virosa.common.service.ArticleService;
import com.rosy.virosa.utilis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UpdateViewCountJob {
    @Autowired
    RedisUtils redisUtils;

    @Autowired
    ArticleService articleService;

    @Scheduled(cron = "0 */1 * * * *")
    public void updateViewCount() {
        Map<String, Integer> articleViewCountMap = redisUtils.getCacheMap("article:viewCount");
        List<Article> articles = articleViewCountMap
                .entrySet()
                .stream()
                .map(entry -> new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue()))
                .toList();

        System.out.println(articles);
        articleService.updateBatchById(articles);
    }
}
