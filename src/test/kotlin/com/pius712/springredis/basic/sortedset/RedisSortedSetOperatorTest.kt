package com.pius712.springredis.basic.sortedset

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RedisSortedSetOperatorTest(
    private val redisSortedSetOperator: RedisSortedSetOperator
) {

    @Test
    fun `데이터는 정렬되어야한다`() {
        // given
        val key = "sorted-set-example1"
        redisSortedSetOperator.add(key, "a", 1.0)
        redisSortedSetOperator.add(key, "b", 3.0)
        redisSortedSetOperator.add(key, "c", 2.0)

        val range = redisSortedSetOperator.range(key, 0, -1)

        Assertions.assertThat(range).containsExactly("a", "c", "b")
    }

    @Test
    fun `데이터 정렬과 점수까지`() {
        val key = "sorted-set-example3"
        redisSortedSetOperator.add(key, "a", 1.0)
        redisSortedSetOperator.add(key, "b", 3.0)
        redisSortedSetOperator.add(key, "c", 2.0)

        val range = redisSortedSetOperator.rangeWithScore(key, 1, 2)

        Assertions.assertThat(range).containsExactly(
            Pair("c", 2.0),
            Pair("b", 3.0)
        )
    }

    @Test
    fun `revrange - 스코어가 큰순으로 정렬된다`() {
        // given
        val key = "sorted-set-example2"
        redisSortedSetOperator.add(key, "a", 1.0)
        redisSortedSetOperator.add(key, "b", 3.0)
        redisSortedSetOperator.add(key, "c", 2.0)

        val range = redisSortedSetOperator.reverseRange(key, 0, -1)

        Assertions.assertThat(range).containsExactly("b", "c", "a")
    }
}