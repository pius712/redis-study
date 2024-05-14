package com.pius712.springredis.usage.searchhisotry

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor


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

    @Test
    fun `검색이 아무리 많이 되어도 5개만 유지된다`() {
        for (i in 0..100_000) {
            searchHistoryService.search(1, getRandomString(10))
        }
        val searchHistory = searchHistoryService.getSearchHistory(1)
        Assertions.assertThat(searchHistory.size).isEqualTo(5)
    }
}

fun getRandomString(length: Int): String {
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
    return (1..length)
        .map { charset.random() }
        .joinToString("")
}