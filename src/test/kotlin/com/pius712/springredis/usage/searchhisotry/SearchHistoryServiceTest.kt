package com.pius712.springredis.usage.searchhisotry

import com.pius712.springredis.domain.usage.searchhisotry.SearchHistoryService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SearchHistoryServiceTest(
    private val searchHistoryService: SearchHistoryService
) {

    @Test
    fun `중복 검색은 나중에 저장된것만 저장되어야 한다`() {
        searchHistoryService.search(1, "first")
        searchHistoryService.search(1, "second")
        searchHistoryService.search(1, "third")
        searchHistoryService.search(1, "fourth")
        searchHistoryService.search(1, "first")

        val searchHistory = searchHistoryService.getSearchHistory(1)
        Assertions.assertThat(searchHistory).containsExactly("fourth", "third", "second", "first")
    }

    /**
     * 멀티 스레딩 하면 훨씬 빠르다.
     * 스레드 32개 기준
     * 100_000개 3초대 걸림.
     * 300_000개 10초. 초당 30_000개
     * 스레드 100개로 하면
     * 300_000개 7초. 초당 42_857개
     * */
    @Test
    fun `검색이 아무리 많이 되어도 5개만 유지된다`() {
        val executorService = Executors.newFixedThreadPool(100)

        val countDownLatch = CountDownLatch(300_000)
        // count time
        val start = System.currentTimeMillis()
        for (i in 0..300_000) {
            executorService.submit {
                try {

                    searchHistoryService.search(1, getRandomString(10))
                } finally {
                    countDownLatch.countDown()
                }
            }
        }
        countDownLatch.await()

        val searchHistory = searchHistoryService.getSearchHistory(1)

        println("time : ${System.currentTimeMillis() - start} ms")
        Assertions.assertThat(searchHistory.size).isEqualTo(5)
    }
}

fun getRandomString(length: Int): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}