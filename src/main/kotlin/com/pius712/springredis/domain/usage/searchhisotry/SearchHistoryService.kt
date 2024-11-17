package com.pius712.springredis.domain.usage.searchhisotry

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class SearchHistoryService(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    /**
     * TODO: 뭔가 성능 문제가 있어보이는데?
     * 10 만개 처리하는데 62초나 걸림
     * range 처리 없어도 30초 정도 걸림
     * */
    fun search(userId: Long, keyword: String) {
        val key = "search_history:${userId}"
        stringRedisTemplate.opsForZSet().add(
            key, keyword,
            Instant.now().toEpochMilli().toDouble()
        )
        stringRedisTemplate.opsForZSet().removeRange(key, 0, -6)
    }

    fun getSearchHistory(userId: Long): List<String> {
        return stringRedisTemplate.opsForZSet().reverseRange("search_history:${userId}", 0, -1)?.toList() ?: emptyList()
    }
}