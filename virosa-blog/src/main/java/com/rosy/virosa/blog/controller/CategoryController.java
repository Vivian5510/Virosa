package com.rosy.virosa.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.entity.Category;
import com.rosy.virosa.common.domain.vo.CategoryListVo;
import com.rosy.virosa.common.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ResponseResult<List<CategoryListVo>> CategoryList() {
        List<Category> list = categoryService.getCategorylist();
        List<CategoryListVo> categoryListVos = BeanUtil.copyToList(list, CategoryListVo.class);
        return ResponseResult.okResult(categoryListVos);
    }
}
