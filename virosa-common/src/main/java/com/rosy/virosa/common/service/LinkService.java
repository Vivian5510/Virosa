package com.rosy.virosa.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.virosa.common.domain.entity.Link;

import java.util.List;

/**
 * 友链(Link)表服务接口
 *
 * @author rosy
 * @since 2024-12-09 13:45:31
 */
public interface LinkService extends IService<Link> {

    List<Link> getAllLink();
}

