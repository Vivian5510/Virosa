package com.rosy.virosa.common.service;


import com.rosy.virosa.common.domain.entity.User;

public interface LoginService {
    String login(User loginUser);

    String logout();
}
