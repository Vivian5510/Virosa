package com.rosy.virosa.common.enums;

import com.rosy.virosa.common.constant.HttpRespCodeConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpRespCodeEnum {
    SUCCESS(HttpRespCodeConstant.SUCCESS, "操作成功"),
    NEED_LOGIN(HttpRespCodeConstant.NEED_LOGIN, "需要登录后操作"),
    NO_OPERATOR_AUTH(HttpRespCodeConstant.NO_OPERATOR_AUTH, "无操作权限"),
    SYSTEM_ERROR(HttpRespCodeConstant.SYSTEM_ERROR, "出现错误"),
    USERNAME_EXIST(HttpRespCodeConstant.USERNAME_EXIST, "用户名已注册"),
    PHONE_NUMBER_EXIST(HttpRespCodeConstant.PHONE_NUMBER_EXIST, "手机号已注册"),
    EMAIL_EXIST(HttpRespCodeConstant.EMAIL_EXIST, "邮箱已注册"),
    REQUIRE_USERNAME(HttpRespCodeConstant.REQUIRE_USERNAME, "必须填写用户名"),
    LOGIN_ERROR(HttpRespCodeConstant.LOGIN_ERROR, "用户名或密码错误");

    private final Integer code;
    private final String msg;
}
