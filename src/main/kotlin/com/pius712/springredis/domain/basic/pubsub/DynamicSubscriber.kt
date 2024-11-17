package com.pius712.springredis.domain.basic.pubsub

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class DynamicSubscriber(
    private val objectMapper: ObjectMapper,
    private val locationTopic: ChannelTopic,
    private val container: RedisMessageListenerContainer,
) {


    fun getLocation(): CompletableFuture<Location> {
        val future = CompletableFuture<Location>()
        container.addMessageListener({ message, pattern ->
            val location = message.body.decodeToString()
            val result = objectMapper.readValue<Location>(location)
            future.complete(result)
        }, locationTopic)
        return future
    }
}