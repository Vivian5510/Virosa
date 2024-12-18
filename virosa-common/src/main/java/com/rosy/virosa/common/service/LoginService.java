package com.rosy.virosa.common.service;


import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.entity.Menu;
import com.rosy.virosa.common.domain.entity.User;
import com.rosy.virosa.common.domain.vo.AdminUserInfoVo;

import java.util.List;

public interface LoginService {
    String login(User loginUser);

    String logout();

    ResponseResult<String> register(User user);

    AdminUserInfoVo getAdminUserInfo(Long id);

    List<Menu> getAdminMenusById(Long id);
}
