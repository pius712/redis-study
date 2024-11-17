package com.pius712.springredis.domain.basic.list

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisListOperator(
    private val stringRedisTemplate: StringRedisTemplate,
) {
    fun leftPush(key: String, value: String) {
        stringRedisTemplate.opsForList().leftPush(key, value)
    }

    fun rightPush(key: String, value: String) {
        val rightPush = stringRedisTemplate.opsForList().rightPush(key, value)
    }

    fun leftPush(key: String, vararg value: String) {
        stringRedisTemplate.opsForList().leftPushAll(key, *value)
    }

    fun range(key: String, start: Long, end: Long): List<String> {
        return stringRedisTemplate.opsForList().range(key, start, end) ?: emptyList()
    }

}