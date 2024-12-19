package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.domain.dto.TagDto;
import com.rosy.virosa.common.domain.entity.Tag;
import com.rosy.virosa.common.mapper.TagMapper;
import com.rosy.virosa.common.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author rosy
 * @since 2024-12-16 22:42:41
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> getTagList(Integer pageNo, Integer pageSize, TagDto tagDto) {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(tagDto.getName()), Tag::getName, tagDto.getName());
        Page<Tag> page = new Page<>(pageNo, pageSize);
        page(page, wrapper);
        return page.getRecords();
    }
}

