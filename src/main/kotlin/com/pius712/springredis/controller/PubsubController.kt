package com.pius712.springredis.controller

import com.pius712.springredis.domain.basic.pubsub.DynamicSubscriber
import com.pius712.springredis.domain.basic.pubsub.Location
import com.pius712.springredis.domain.basic.pubsub.LocationUpdatedNotifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pubsub")
class PubsubController(
    private val dynamicSubscriber: DynamicSubscriber,
    private val notifier: LocationUpdatedNotifier
) {

    @GetMapping("/location")
    fun getLocation(): Location {
        val result = dynamicSubscriber.getLocation()
        return result.get()
    }

    @PostMapping("/location/update")
    fun updateLocation() {
        notifier.notify(Location(1, 37.7749, 122.4194))
    }
}