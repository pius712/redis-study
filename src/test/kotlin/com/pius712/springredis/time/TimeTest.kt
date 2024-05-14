package com.pius712.springredis.time

import org.junit.jupiter.api.Test
import java.time.Instant
import java.time.LocalDateTime


class TimeTest {

    @Test
    fun `기본 local date time`() {
        val now = LocalDateTime.now()
        // yyyy-MM-ddTHH:mm:ss.SSS
        println("localDateTime: $now")

        // yyyy-MM-dd
        println("localDate ${now.toLocalDate()}")

        // HH:mm:ss.SSS
        println("localTime: ${now.toLocalTime()}")
    }

    @Test
    fun `millis`() {
        val now = Instant.now()
        println("time millis: ${now.toEpochMilli()}")

        val now2 = System.currentTimeMillis()
        println("time millis: $now2")
        
    }
}