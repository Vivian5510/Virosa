package com.rosy.virosa.utilis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = {"unchecked", "rawtypes"})
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public <T> void setCacheObject(final String key, final T value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            log.info("成功缓存对象，key: {}", key);
        } catch (Exception e) {
            log.error("缓存对象失败，key: {}", key, e);
        }
    }

    /**
     * 缓存基本的对象，带过期时间
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
            log.info("成功缓存对象，key: {}, 有效期: {} {}", key, timeout, timeUnit);
        } catch (Exception e) {
            log.error("缓存对象失败，key: {}", key, e);
        }
    }

    /**
     * 获取缓存的基本对象
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public Object getCacheObject(final String key) {
        try {
            ValueOperations<String, Object> operation = redisTemplate.opsForValue();
            return operation.get(key);
        } catch (Exception e) {
            log.error("获取缓存对象失败，key: {}", key, e);
            return null;
        }
    }

    /**
     * 设置缓存有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        try {
            boolean result = redisTemplate.expire(key, timeout, unit);
            log.info("设置缓存有效期，key: {}, 有效期: {} {}", key, timeout, unit);
            return result;
        } catch (Exception e) {
            log.error("设置缓存有效期失败，key: {}", key, e);
            return false;
        }
    }

    /**
     * 删除单个对象
     *
     * @param key 缓存键
     * @return true=删除成功；false=删除失败
     */
    public boolean deleteObject(final String key) {
        try {
            boolean result = redisTemplate.delete(key);
            log.info("删除缓存对象，key: {}, 成功: {}", key, result);
            return result;
        } catch (Exception e) {
            log.error("删除缓存对象失败，key: {}", key, e);
            return false;
        }
    }

    /**
     * 批量删除对象
     *
     * @param collection 多个键
     * @return 删除的对象个数
     */
    public long deleteObject(final Collection<String> collection) {
        if (collection == null || collection.isEmpty()) {
            log.warn("删除缓存失败，传入的集合为空");
            return 0;
        }
        try {
            long deletedCount = redisTemplate.delete(collection);
            log.info("批量删除缓存对象，数量: {}", deletedCount);
            return deletedCount;
        } catch (Exception e) {
            log.error("批量删除缓存对象失败", e);
            return 0;
        }
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存键
     * @param dataList 待缓存的List数据
     */
    public <T> void setCacheList(final String key, final List<T> dataList) {
        try {
            redisTemplate.opsForList().rightPushAll(key, dataList);
            log.info("缓存List数据，key: {}", key);
        } catch (Exception e) {
            log.error("缓存List数据失败，key: {}", key, e);
        }
    }

    /**
     * 获取缓存的List数据
     *
     * @param key 缓存键
     * @return 缓存的List数据
     */
    public <T> List<T> getCacheList(final String key) {
        try {
            return redisTemplate.opsForList().range(key, 0, -1);
        } catch (Exception e) {
            log.error("获取List数据失败，key: {}", key, e);
            return Collections.emptyList();
        }
    }


    /**
     * 缓存Map数据
     *
     * @param key     缓存键
     * @param dataMap 待缓存的Map数据
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap) {
        try {
            redisTemplate.opsForHash().putAll(key, dataMap);
            log.info("缓存Map数据，key: {}", key);
        } catch (Exception e) {
            log.error("缓存Map数据失败，key: {}", key, e);
        }
    }

    /**
     * 获取缓存的Map数据
     *
     * @param key 缓存键
     * @return 缓存的Map数据
     */
    public <T> Map<String, T> getCacheMap(final String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            log.error("获取Map数据失败，key: {}", key, e);
            return Collections.emptyMap();
        }
    }

    public void incrementCacheMapValue(String key, String hKey, long v) {
        redisTemplate.boundHashOps(key).increment(hKey, v);
    }

    public Object getCacheMapValue(String key, Long id) {
        return redisTemplate.opsForHash().get(key, id.toString());
    }
}