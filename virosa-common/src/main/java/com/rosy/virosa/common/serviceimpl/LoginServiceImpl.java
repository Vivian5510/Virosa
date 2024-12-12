package com.rosy.virosa.common.serviceimpl;

import cn.hutool.jwt.JWTUtil;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.security.CustomUserDetails;
import com.rosy.virosa.common.service.LoginService;
import com.rosy.virosa.utilis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public String login(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        if (authentication == null) {
            throw new UsernameNotFoundException("用户名或密码错误！");
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUser().getId();
        String jwt = JWTUtil.createToken(
                Map.ofEntries(
                        Map.entry("sub", userId),
                        Map.entry("expire_time", System.currentTimeMillis() + 1000 * 60 * 60)
                ),
                "Rosy".getBytes()
        );
        redisUtils.setCacheObject("login:" + userId, userDetails);
        return jwt;
    }

    @Override
    public String logout() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getUser().getId();
        redisUtils.deleteObject("login:" + userId);
        return userDetails.getUser().getUserName();
    }
}
