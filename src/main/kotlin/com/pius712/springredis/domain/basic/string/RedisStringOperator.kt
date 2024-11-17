package com.pius712.springredis.domain.basic.string

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisStringOperator(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    fun set(key: String, value: String) {
        stringRedisTemplate.opsForValue().set(key, value)
    }

    fun get(key: String): String? {
        return stringRedisTemplate.opsForValue().get(key)
    }

    fun setNx(key: String, value: String): Boolean? {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value)
    }

    fun delete(key: String) {
        stringRedisTemplate.delete(key)
    }

    fun incr(key: String): Long? {
        return stringRedisTemplate.opsForValue().increment(key)
    }

    fun incrBy(key: String, value: Long): Long? {
        return stringRedisTemplate.opsForValue().increment(key, value)
    }

    fun mget(keys: List<String>): List<String> {
        return stringRedisTemplate.opsForValue().multiGet(keys) ?: emptyList()
    }

    fun mset(map: Map<String, String>) {
        stringRedisTemplate.opsForValue().multiSet(map)
    }
}