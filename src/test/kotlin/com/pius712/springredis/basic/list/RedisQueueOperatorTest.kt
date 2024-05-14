package com.pius712.springredis.basic.list

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RedisQueueOperatorTest(
    private val redisQueueOperator: RedisQueueOperator
) {

    @Test
    fun `queue 사이즈 까지만 넣을 수 있다`() {
        redisQueueOperator.changeSize(3)
        redisQueueOperator.push("queue", "1")
        redisQueueOperator.push("queue", "2")
        redisQueueOperator.push("queue", "3")
        redisQueueOperator.push("queue", "4")
        redisQueueOperator.push("queue", "5")

        val size = redisQueueOperator.size("queue")
        assertEquals(3, size)
    }
}