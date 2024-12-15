package com.rosy.virosa.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rosy.virosa.common.domain.entity.User;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author rosy
 * @since 2024-12-12 22:26:02
 */
public interface UserService extends IService<User> {
    List<String> getPermissionsById(Long id);

    boolean isUserNameExisted(String userName);

    boolean isEmailExisted(String email);
}

