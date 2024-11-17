package com.pius712.springredis.domain.basic.hash

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisHashOperator(
    private val redisTemplate: StringRedisTemplate,
) {


}