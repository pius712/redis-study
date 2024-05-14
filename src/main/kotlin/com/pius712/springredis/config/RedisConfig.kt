package com.pius712.springredis.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.pius712.springredis.TestData
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Bean
    fun config(redisConnectionFactory: RedisConnectionFactory) : RedisTemplate<String, TestData> {
        val template = RedisTemplate<String, TestData>()

        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
        // Configure ObjectMapper for Kotlin
        val objectMapper = ObjectMapper().registerKotlinModule()
        template.valueSerializer = Jackson2JsonRedisSerializer(objectMapper, TestData::class.java)
//        template.valueSerializer = GenericJackson2JsonRedisSerializer()

        return template
    }
}