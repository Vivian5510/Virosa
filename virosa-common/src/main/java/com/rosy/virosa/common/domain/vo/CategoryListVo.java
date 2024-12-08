package com.rosy.virosa.common.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类表(Category)表实体类
 *
 * @author rosy
 * @since 2024-12-08 11:09:51
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListVo {
    private Long id;
    // 分类名
    private String name;
}

