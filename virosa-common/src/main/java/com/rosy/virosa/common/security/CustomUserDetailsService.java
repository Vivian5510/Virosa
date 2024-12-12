package com.rosy.virosa.common.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User loginUser = userService.getOne(queryWrapper);
        if (loginUser == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        List<String> permissions = userService.getPermissionsById(loginUser.getId());
        return new CustomUserDetails(loginUser, permissions);
    }
}
