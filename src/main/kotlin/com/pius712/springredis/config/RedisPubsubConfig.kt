package com.pius712.springredis.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.pius712.springredis.domain.basic.pubsub.Location
import com.pius712.springredis.domain.basic.pubsub.LocationSubscriber
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisPubsubConfig(
    private val objectMapper: ObjectMapper,
    private val locationSubscriber: LocationSubscriber
) {


    @Bean
    fun messageListener(): MessageListenerAdapter {
        return MessageListenerAdapter(locationSubscriber)
    }

    @Bean
    fun redisMessageListenerContainer(redisConnectionFactory: RedisConnectionFactory): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(redisConnectionFactory)
//        container.addMessageListener(messageListener(), locationTopic())
        return container
    }

    @Bean
    fun locationRedisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<Long, Location> {
        val template = RedisTemplate<Long, Location>()
        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = Jackson2JsonRedisSerializer(objectMapper, Location::class.java)
        return template
    }


    @Bean
    fun locationTopic(): ChannelTopic {
        return ChannelTopic("location:updated")
    }
}