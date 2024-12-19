package com.rosy.virosa.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.virosa.common.domain.dto.TagDto;
import com.rosy.virosa.common.domain.entity.Tag;

import java.util.List;

/**
 * 标签(Tag)表服务接口
 *
 * @author rosy
 * @since 2024-12-16 22:42:41
 */
public interface TagService extends IService<Tag> {

    List<Tag> getTagList(Integer pageNo, Integer pageSize, TagDto tagDto);
}

