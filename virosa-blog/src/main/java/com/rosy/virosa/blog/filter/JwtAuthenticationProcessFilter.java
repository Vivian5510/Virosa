package com.rosy.virosa.blog.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.rosy.virosa.common.security.CustomUserDetails;
import com.rosy.virosa.utilis.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
public class JwtAuthenticationProcessFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info(request.getRequestURI());
        String token = request.getHeader("token");
        //如果没有携带token，或者携带的token为空的话就放行
        if (Objects.nonNull(token) && StringUtils.hasText(token)) {
            try {
                //解析token
                JWT jwt = JWTUtil.parseToken(token);
                String userId = jwt.getPayload("sub").toString();

                //从redis中获取用户信息
                String redisKey = "login:" + userId;
                CustomUserDetails userDetails = BeanUtil.copyProperties(redisUtils.getCacheObject(redisKey), CustomUserDetails.class);

                // 将用户信息存入SecurityContextHolder
                if (userDetails != null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.warn("Redis中没有找到对应的用户信息");
                }
            } catch (Exception e) {
                log.warn("用户发送的token非法");
            }
        }

        filterChain.doFilter(request, response);
    }
}
