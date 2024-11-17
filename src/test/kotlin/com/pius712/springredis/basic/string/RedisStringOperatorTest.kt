package com.pius712.springredis.basic.string

import com.pius712.springredis.domain.basic.string.RedisStringOperator
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RedisStringOperatorTest(
    private val redisStringOperator: RedisStringOperator
) {

    val key = "string-test:1"


    @Test
    fun `NX는 있으면 저장하지 않는다`() {

        redisStringOperator.set(key, "test-value")
        redisStringOperator.setNx(key, "test-value2")
        val found = redisStringOperator.get(key)
        Assertions.assertThat(found).isEqualTo("test-value")
    }

    @Test
    fun `값을 증가시킨다`() {
        val key = "string-test:2"
        redisStringOperator.set(key, "1")
        redisStringOperator.incr(key)
        val toBeTwo = redisStringOperator.get(key)
        Assertions.assertThat(toBeTwo).isEqualTo("2")

        redisStringOperator.incrBy(key, 3);
        val toBeFive = redisStringOperator.get(key)

        Assertions.assertThat(toBeFive).isEqualTo("5")
    }

    @Test
    fun `mset, mget`() {

        redisStringOperator.mset(mapOf("string-test:3" to "1", "string-test:4" to "2"))
        val mget = redisStringOperator.mget(listOf("string-test:3", "string-test:4"))
        Assertions.assertThat(mget).containsExactly("1", "2")
    }

}