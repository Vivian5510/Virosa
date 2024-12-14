package com.rosy.virosa.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.domain.vo.UserInfoVo;
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
    public ResponseResult<UserInfoVo> userInfo() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityUtils.getUserDetails();
        UserInfoVo userInfoVo = BeanUtil.copyProperties(userService.getById(userDetails.getUser().getId()), UserInfoVo.class);
        return ResponseResult.okResult(userInfoVo);
    }

    @PutMapping
    public ResponseResult<String> updateUserInfo(@RequestBody User user) {
        userService.updateById(user);
        return ResponseResult.okResult();
    }
}
