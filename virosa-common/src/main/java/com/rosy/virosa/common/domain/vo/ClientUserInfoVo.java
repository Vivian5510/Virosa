package com.rosy.virosa.common.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUserInfoVo {
    private String userName;
    // 昵称
    private String nickName;

    // 用户性别（0男，1女，2未知）
    private String sex;
    // 头像
    private String avatar;
}
