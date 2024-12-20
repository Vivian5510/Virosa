package com.rosy.virosa.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserInfoVo {
    // 主键
    private Long id;
    // 昵称
    private String nickName;
    // 邮箱
    private String email;
    // 用户性别（0男，1女，2未知）
    private String sex;
    // 头像
    private String avatar;

    private List<String> permissions;

    private List<String> roles;
}
