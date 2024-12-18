package com.rosy.virosa.common.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rosy.virosa.common.domain.entity.Menu;
import com.rosy.virosa.common.mapper.MenuMapper;
import com.rosy.virosa.common.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author rosy
 * @since 2024-12-12 22:46:10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<Menu> getSubMenus(Long id) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getParentId, id);
        return list(queryWrapper);
    }
}

