package com.rosy.virosa.common.handler;

import com.rosy.virosa.common.domain.ResponseResult;
import com.rosy.virosa.common.domain.SystemException;
import com.rosy.virosa.common.enums.AppHttpStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult<String> systemExceptionHandler(SystemException e) {
        log.error("SystemException occurred: {}", e.getMessage(), e);

        return ResponseResult.errorResult(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<String> exceptionHandler(Exception e) {
        log.error("Exception occurred: {}", e.getMessage(), e);

        return ResponseResult.errorResult(AppHttpStatusEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}
