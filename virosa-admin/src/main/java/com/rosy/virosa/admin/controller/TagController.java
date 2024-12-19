package com.rosy.virosa.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.dto.TagDto;
import com.rosy.virosa.common.domain.entity.Tag;
import com.rosy.virosa.common.domain.vo.PageVo;
import com.rosy.virosa.common.domain.vo.TagVo;
import com.rosy.virosa.common.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/list")
    public ResponseResult list(
            @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(value = "pageNo", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "name", defaultValue = "") TagDto tagDto) {
        List<TagVo> tagVos = BeanUtil.copyToList(tagService.getTagList(pageNo, pageSize, tagDto), TagVo.class);
        PageVo<TagVo> pageVo = new PageVo<>(tagVos, tagVos.size());
        return ResponseResult.okResult(pageVo);
    }

    @PostMapping
    public ResponseResult addOrUpdateTag(@RequestBody Tag tag) {
        tagService.saveOrUpdate(tag);
        return ResponseResult.okResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable Long id) {
        tagService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult updateTag(@PathVariable Long id) {
        TagVo tagVo = BeanUtil.copyProperties(tagService.getById(id), TagVo.class);
        return ResponseResult.okResult(tagVo);
    }
}
