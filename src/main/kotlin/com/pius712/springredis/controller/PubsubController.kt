package com.pius712.springredis.controller

import com.pius712.springredis.domain.basic.pubsub.Location
import com.pius712.springredis.domain.basic.pubsub.LocationUpdatedNotifier
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/pubsub")
class PubsubController(
    private val notifier: LocationUpdatedNotifier
) {

    @PostMapping("/location/update")
    fun updateLocation() {
        notifier.notify(Location(1, 37.7749, 122.4194))
    }
}