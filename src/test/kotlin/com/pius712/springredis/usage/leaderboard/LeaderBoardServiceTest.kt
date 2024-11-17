package com.pius712.springredis.usage.leaderboard

import com.pius712.springredis.domain.usage.leaderboard.LeaderBoardService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor
import java.lang.Math.random
import kotlin.math.floor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class LeaderBoardServiceTest(
    private val leaderBoardService: LeaderBoardService
) {
    @Test
    fun `점수 순 랭킹을 구할 수 있다`() {
        leaderBoardService.setScore(1, 350.0)
        leaderBoardService.setScore(2, 200.0)
        leaderBoardService.setScore(3, 300.0)
        leaderBoardService.setScore(4, 400.0)
        leaderBoardService.setScore(5, 500.0)

        Assertions.assertThat(leaderBoardService.getDailyRankByUser(1)).isEqualTo(
            3
        )
    }

    @Test
    fun `순위에 따른 랭커를 구할 수 있다`() {
        for (i in 1L..100) {
            leaderBoardService.setScore(i, floor(random() * 1000))
        }

        val dailyRank = leaderBoardService.getDailyRank(1, 3)
        println(dailyRank)
    }
}