package com.rosy.virosa.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.domain.vo.ClientUserInfoVo;
import com.rosy.virosa.common.security.CustomUserDetails;
import com.rosy.virosa.common.service.UserService;
import com.rosy.virosa.utilis.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult<ClientUserInfoVo> userInfo() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityUtils.getUserDetails();
        ClientUserInfoVo clientUserInfoVo = BeanUtil.copyProperties(userService.getById(userDetails.getUser().getId()), ClientUserInfoVo.class);
        return ResponseResult.okResult(clientUserInfoVo);
    }

    @PutMapping
    public ResponseResult<String> updateUserInfo(@RequestBody User user) {
        userService.updateById(user);
        return ResponseResult.okResult();
    }
}
