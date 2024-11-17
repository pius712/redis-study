package com.pius712.springredis.domain.usage.leaderboard

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class LeaderBoardService(
    private val stringRedisTemplate: StringRedisTemplate
) {

    fun setScore(userId: Long, score: Double) {
        stringRedisTemplate.opsForZSet().incrementScore(
            "daily-score:${
                LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd")
                )
            }", userId.toString(), score
        )
    }


    fun getDailyRankByUser(userId: Long): Long {
        return stringRedisTemplate.opsForZSet().reverseRank(
            "daily-score:${
                LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyyMMdd")
                )
            }", userId.toString()
        )!! + 1
    }

    fun getDailyRank(from: Int, to: Int): List<String> {
        return stringRedisTemplate.opsForZSet().range(
            "daily-score:${LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))}",
            from.toLong(),
            to.toLong()
        )?.toList() ?: emptyList()
    }
}