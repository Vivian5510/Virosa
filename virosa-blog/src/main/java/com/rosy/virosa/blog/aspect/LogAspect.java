package com.rosy.virosa.blog.aspect;

import com.alibaba.fastjson2.JSON;
import com.rosy.virosa.blog.annotation.APILogTag;
import com.rosy.virosa.common.domain.ResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(com.rosy.virosa.blog.annotation.APILogTag)")
    public void pt() {

    }

    @Around("pt()")
    public ResponseResult APILog(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        ResponseResult result;

        try {
            log.info("=======START=======");
            // 打印请求 URL
            log.info("URL            : {}", request.getRequestURL().toString());
            // 打印描述信息
            log.info("BusinessName   : {}", getBusinessName(joinPoint));
            // 打印 Http method
            log.info("HTTP Method    : {}", request.getMethod());
            // 打印调用 controller 的全路径以及执行方法
            log.info("Class Method   : {}.{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
            // 打印请求的 IP
            log.info("IP             : {}", request.getRemoteAddr());
            // 打印请求入参
            log.info("Request Args   : {}", JSON.toJSONString(joinPoint.getArgs()));

            result = (ResponseResult) joinPoint.proceed();

            // 打印出参
            log.info("Response       : {}", JSON.toJSONString(result));
        } finally {
            log.info("========End========{}", System.lineSeparator());
        }

        return result;
    }

    private String getBusinessName(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(APILogTag.class).value();
    }
}

