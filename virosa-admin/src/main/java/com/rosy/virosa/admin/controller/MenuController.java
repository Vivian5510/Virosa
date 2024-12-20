package com.rosy.virosa.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.entity.Menu;
import com.rosy.virosa.common.domain.vo.MenuVo;
import com.rosy.virosa.common.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu) {
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @PutMapping("/{id}")
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteMenu(@RequestBody Menu menu) {
        menuService.removeById(menu.getId());
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getMenu(@PathVariable Long id) {
        return ResponseResult.okResult(BeanUtil.copyProperties(menuService.getById(id), MenuVo.class));
    }
}
