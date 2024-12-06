package com.rosy.virosa.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章表(Article)表实体类
 *
 * @author rosy
 * @since 2024-12-06 13:39:53
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {

    private Long id;
    //标题
    private String title;

    private Long viewCount;
}

