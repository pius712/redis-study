package com.pius712.springredis.domain.basic.pubsub

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component

@Component
class LocationSubscriber : MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        val location = message.body.decodeToString()
        println("Location updated: $location")
    }
}