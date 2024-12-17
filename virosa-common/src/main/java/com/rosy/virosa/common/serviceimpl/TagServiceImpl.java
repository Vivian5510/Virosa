package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.domain.entity.Tag;
import com.rosy.virosa.common.mapper.TagMapper;
import com.rosy.virosa.common.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author rosy
 * @since 2024-12-16 22:42:41
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

