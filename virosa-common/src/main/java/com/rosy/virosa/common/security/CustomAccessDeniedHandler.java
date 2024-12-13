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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error(Throwables.getStackTraceAsString(accessDeniedException));

        ResponseResult result = ResponseResult.errorResult(AppHttpStatusEnum.NO_OPERATOR_AUTH);
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
