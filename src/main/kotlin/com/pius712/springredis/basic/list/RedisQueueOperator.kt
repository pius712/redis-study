package com.pius712.springredis.basic.list

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisQueueOperator(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    private var defaultSize = 5;


    fun changeSize(size: Int) {
        defaultSize = size;
    }

    fun push(key: String, value: String) {
        stringRedisTemplate.opsForList().leftPush(key, value)
        stringRedisTemplate.opsForList().trim(key, 0, defaultSize.toLong() - 1)
    }

    fun size(key: String): Long? {
        return stringRedisTemplate.opsForList().size(key)
    }
}