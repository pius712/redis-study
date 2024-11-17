package com.pius712.springredis.domain.basic.pubsub

import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

@Component
class LocationUpdatedNotifier(
    private val locationClient: LocationClient,
    private val topic: ChannelTopic
) {

    fun notify(location: Location) {
        locationClient.publish(location)
    }
}