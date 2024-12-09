package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.constant.LinkStatusConstant;
import com.rosy.virosa.common.domain.entity.Link;
import com.rosy.virosa.common.mapper.LinkMapper;
import com.rosy.virosa.common.service.LinkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author rosy
 * @since 2024-12-09 13:45:31
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public List<Link> getAllLink() {
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, LinkStatusConstant.LINK_STATUS_APPROVED);
        return list(queryWrapper);
    }
}

