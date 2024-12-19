package com.rosy.virosa.blog.controller;

import com.rosy.virosa.blog.annotation.APILogTag;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    //TODO: 把GET请求和POST请求分开：GET请求返回登录页面 POST请求提交表单
    @APILogTag("用户登录")
    @PostMapping("/user/login")
    public ResponseResult<Map<String, String>> login(@RequestBody User loginUser) {
        String jwt = loginService.login(loginUser);
        return ResponseResult.okResult(Map.of("token", jwt));
    }

    @PostMapping("user/logout")
    public ResponseResult<String> logout() {
        String userName = loginService.logout();
        return ResponseResult.okResult(userName);
    }

    @PostMapping("/user/register")
    public ResponseResult<String> register(@RequestBody User user) {
        return loginService.register(user);
    }
}
