package com.rosy.virosa.common.constant;

public class AppHttpStatusConstant {
    public static final int SUCCESS = 200;

    public static final int NEED_LOGIN = 401;
    public static final int NO_OPERATOR_AUTH = 403;

    public static final int SYSTEM_ERROR = 500;
    public static final int USERNAME_EXIST = 501;
    public static final int PHONE_NUMBER_EXIST = 502;
    public static final int EMAIL_EXIST = 503;

    public static final int REQUIRE_USERNAME = 504;
    public static final int LOGIN_ERROR = 505;

    public static final int PARAM_INVALID = 602;
    public static final Integer PARAM_NOT_NULL = 603;
}
