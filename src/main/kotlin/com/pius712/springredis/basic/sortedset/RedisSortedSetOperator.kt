package com.pius712.springredis.basic.sortedset

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations.TypedTuple
import org.springframework.stereotype.Component

@Component
class RedisSortedSetOperator(
    private val redisTemplate: RedisTemplate<String, String>
) {

    // ZADD KEY SCORE VALUE
    fun add(key: String, value: String, score: Double) {
        redisTemplate.opsForZSet().add(key, value, score)
    }

    // ZADD KEY SCORE1 VALUE1 SCORE2 VALUE2 ...
    fun add(key: String, vararg value: Pair<String, Double>) {
        val element = value.map {
            TypedTuple.of(it.first, it.second)
        }.toSet()
        redisTemplate.opsForZSet().add(key, element)
    }

    fun addNX(key: String, value: String, score: Double): Boolean? {
        return redisTemplate.opsForZSet().addIfAbsent(key, value, score)
    }

    fun range(key: String, start: Long, end: Long): List<String> {
        return redisTemplate.opsForZSet().range(key, start, end)?.toList() ?: emptyList()
    }

    fun rangeWithScore(key: String, start: Long, end: Long): List<Pair<String, Double>> {
        return redisTemplate.opsForZSet().rangeWithScores(key, start, end)?.map {
            Pair(it.value!!, it.score!!)
        } ?: emptyList()
    }

    fun reverseRange(key: String, start: Long, end: Long): List<String> {
        return redisTemplate.opsForZSet().reverseRange(key, start, end)?.toList() ?: emptyList()
    }
}