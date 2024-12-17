package com.rosy.virosa.admin.controller;

import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping("/test")
    public ResponseResult TestAdmin() {
        return ResponseResult.okResult(tagService.list());
    }
}
