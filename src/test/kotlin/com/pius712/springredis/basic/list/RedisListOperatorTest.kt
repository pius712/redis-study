package com.pius712.springredis.basic.list

import com.pius712.springredis.domain.basic.list.RedisListOperator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RedisListOperatorTest(
    private val redisListOperator: RedisListOperator
) {
    @Test
    fun `리스트에 데이터 추가`() {
        redisListOperator.leftPush("list", "1")
        redisListOperator.leftPush("list", "2")
        redisListOperator.leftPush("list", "3")

        val list = redisListOperator.range("list", 0, -1)
        assertEquals(listOf("3", "2", "1"), list)
    }
}