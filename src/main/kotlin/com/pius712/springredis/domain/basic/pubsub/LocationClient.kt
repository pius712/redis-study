package com.pius712.springredis.domain.basic.pubsub

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class LocationClient(
    private val channelTopic: ChannelTopic,
    private val redisTemplate: RedisTemplate<Long, Location>
) {

    fun publish(location: Location) {
        redisTemplate.convertAndSend(channelTopic.topic, location)
    }
}