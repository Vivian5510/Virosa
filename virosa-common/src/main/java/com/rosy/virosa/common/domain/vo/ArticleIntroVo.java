package com.rosy.virosa.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleIntroVo {
    private Long id;
    //标题
    private String title;
    //文章摘要
    private String summary;
    //缩略图
    private String thumbnail;
    //访问量
    private Long viewCount;
    //分类名
    private String categoryName;

    private Long createBy;

    private Date createTime;
}
