package com.rosy.virosa.common.config;

import com.rosy.virosa.common.serializer.FastJson2RedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        FastJson2RedisSerializer fastJson2RedisSerializer = new FastJson2RedisSerializer();

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(fastJson2RedisSerializer);

        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(fastJson2RedisSerializer);

        //确保所有属性都加载完毕之后，进行检查，根据检查结果进行反馈或者修补
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
