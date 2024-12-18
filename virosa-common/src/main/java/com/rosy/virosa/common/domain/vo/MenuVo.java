package com.rosy.virosa.common.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo {
    @TableId
    private Long id;
    // 菜单名
    private String menuName;
    // 路由地址
    private String path;
    // 组件路径
    private String component;
    // 菜单状态（0显示 1隐藏）
    private String visible;
    // 菜单状态（0正常 1停用）
    private String status;
    // 权限标识
    private String perms;
    // 菜单图标
    private String icon;
    //父菜单Id
    private Long parentId;
    //菜单类型
    private String menuType;
    //菜单顺序
    private String orderNum;

    private Date createTime;

    List<MenuVo> SubMenus = new ArrayList<>();
}
