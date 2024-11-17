package com.pius712.springredis.usage.randompick

import com.pius712.springredis.domain.usage.randompick.RandomPickService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestConstructor

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class RandomPickServiceTest(
    private val randomPickService: RandomPickService
) {


    @BeforeEach
    fun setup() {
        randomPickService.addMembers(
            "girlgroup:worldcup",
            "karina", "winter", "gisele", "ningning", "aespa", "wonyoung", "yujin", "minju", "chaeyeon", "eunbi",
            "irene", "seulgi", "wendy", "joy", "yeri", "redvelvet", "jennie", "lisa", "jisoo", "rose",
        )
    }

    @Test
    fun `원하는 갯수만큼 랜덤으로 가져와야한다`() {
        val randomMembers = randomPickService.pickRandom("girlgroup:worldcup", 8)

        Assertions.assertThat(randomMembers.size).isEqualTo(8)
    }
}