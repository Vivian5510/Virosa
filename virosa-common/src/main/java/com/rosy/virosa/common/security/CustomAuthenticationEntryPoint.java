package com.rosy.virosa.common.security;

import com.alibaba.fastjson2.JSON;
import com.google.common.base.Throwables;
import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.enums.AppHttpStatusEnum;
import com.rosy.virosa.utilis.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error(Throwables.getStackTraceAsString(authException));

        ResponseResult<String> result = null;
        if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseResult.errorResult(AppHttpStatusEnum.NEED_LOGIN);
        } else if (authException instanceof BadCredentialsException) {
            result = ResponseResult.errorResult(AppHttpStatusEnum.LOGIN_ERROR);
        } else {
            result = ResponseResult.errorResult(AppHttpStatusEnum.SYSTEM_ERROR);
        }

        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
