package com.rosy.virosa.common.serializer;


import com.alibaba.fastjson2.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class FastJson2RedisSerializer implements RedisSerializer<Object> {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    @Override
    public byte[] serialize(Object value) throws SerializationException {
        if (value == null) {
            return new byte[0];
        }

        return JSON.toJSONBytes(value, DEFAULT_CHARSET);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        String jStr = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(jStr, Object.class);
    }
}

