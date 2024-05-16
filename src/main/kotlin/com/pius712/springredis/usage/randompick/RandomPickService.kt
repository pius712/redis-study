package com.pius712.springredis.usage.randompick

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RandomPickService(
    private val redisTemplate: StringRedisTemplate,
) {


    fun addMembers(key: String, vararg members: String) {
        redisTemplate.opsForSet().add(key, *members)
    }

    fun pickRandom(key: String, count: Long): List<String> {
        return redisTemplate.opsForSet().distinctRandomMembers(key, count)?.toList() ?: emptyList()
    }
}