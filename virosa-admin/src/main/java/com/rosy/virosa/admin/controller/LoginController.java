package com.rosy.virosa.admin.controller;


import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.SystemException;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.domain.vo.AdminUserInfoVo;
import com.rosy.virosa.common.enums.AppHttpStatusEnum;
import com.rosy.virosa.common.security.CustomUserDetails;
import com.rosy.virosa.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/admin/login")
    public ResponseResult<String> login(@RequestBody User user) {
        //参数校验
        if (!StringUtils.hasText(user.getUserName())) {
            throw new SystemException(AppHttpStatusEnum.REQUIRE_USERNAME);
        } else if (StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpStatusEnum.REQUIRE_PASSWORD);
        }

        String token = loginService.login(user);
        return ResponseResult.okResult(token);
    }

    @GetMapping("/admin/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo() {
        CustomUserDetails userDatails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long id = userDatails.getUser().getId();
        AdminUserInfoVo adminUserInfoVo = loginService.getAdminUserInfo(id);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/admin/getRouters")
    public ResponseResult<String> getAdminRouters() {
        return ResponseResult.okResult();
    }
}
