package com.rosy.virosa.admin.controller;


import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.SystemException;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.domain.vo.AdminUserInfoVo;
import com.rosy.virosa.common.domain.vo.MenuVo;
import com.rosy.virosa.common.enums.AppHttpStatusEnum;
import com.rosy.virosa.common.security.CustomUserDetails;
import com.rosy.virosa.common.service.LoginService;
import com.rosy.virosa.common.service.MenuService;
import com.rosy.virosa.utilis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    MenuService menuService;

    @PostMapping("/admin/login")
    public ResponseResult<String> login(@RequestBody User user) {
        //参数校验
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpStatusEnum.REQUIRE_USERNAME);
        } else if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpStatusEnum.REQUIRE_PASSWORD);
        }

        String token = loginService.login(user);
        return ResponseResult.okResult(token);
    }

    @PostMapping("user/logout")
    public ResponseResult<String> logout() {
        String userName = loginService.logout();
        return ResponseResult.okResult(userName);
    }

    @GetMapping("/admin/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityUtils.getUserDetails();
        Long id = userDetails.getUser().getId();
        AdminUserInfoVo adminUserInfoVo = loginService.getAdminUserInfo(id);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/admin/getRouters")
    public ResponseResult<List<MenuVo>> getAdminRouters() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityUtils.getUserDetails();
        Long id = userDetails.getUser().getId();
        List<MenuVo> menus = BeanUtil.copyToList(loginService.getAdminMenusById(id), MenuVo.class);
        for (MenuVo menuVo : menus) {
            List<MenuVo> subMenus = BeanUtil.copyToList(menuService.getSubMenus(menuVo.getId()), MenuVo.class);
            menuVo.setSubMenus(subMenus);
        }

        return ResponseResult.okResult(menus);
    }

}
