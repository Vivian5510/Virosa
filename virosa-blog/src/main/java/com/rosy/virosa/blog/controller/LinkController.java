package com.rosy.virosa.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.vo.LinkDisplayVo;
import com.rosy.virosa.common.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    LinkService linkService;

    @RequestMapping("/getAllLink")
    public ResponseResult<List<LinkDisplayVo>> getAllLink() {
        List<LinkDisplayVo> LinkDisplayVos = BeanUtil.copyToList(linkService.getAllLink(), LinkDisplayVo.class);
        return ResponseResult.okResult(LinkDisplayVos);
    }
}
