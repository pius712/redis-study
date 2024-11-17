package com.pius712.springredis.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.pius712.springredis.TestData
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Bean
    fun config(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, TestData> {
        val template = RedisTemplate<String, TestData>()

        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
        val objectMapper = ObjectMapper().registerKotlinModule()
        template.valueSerializer = Jackson2JsonRedisSerializer(objectMapper, TestData::class.java)

        return template
    }


    @Bean
    fun listRedis(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, List<String>> {
        val template = RedisTemplate<String, List<String>>()

        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
        val objectMapper = ObjectMapper().registerKotlinModule()
        template.valueSerializer = Jackson2JsonRedisSerializer(objectMapper, List::class.java)
        return template
    }
}