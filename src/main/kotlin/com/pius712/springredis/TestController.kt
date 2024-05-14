package com.pius712.springredis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/test")
class TestController(
    private val redisTemplate: RedisTemplate<String, TestData>,
    private val stringRedisTemplate: StringRedisTemplate
) {

    @GetMapping
    fun getTestData(): TestData {

        val cached = redisTemplate.opsForValue().get("test")
        if (cached != null) {
            return cached
        }

        val test = TestData("name", 32)
        redisTemplate
            .opsForValue()
            .set("test", test);
        return test
    }

    @GetMapping
    fun getTestData2(): TestData {

        val cached = stringRedisTemplate.opsForValue().get("test")
        if (cached != null) {
            return cached
        }

        val test = TestData("name", 32)
        stringRedisTemplate
            .opsForValue()
            .set("test", test);
        return test

    }


}