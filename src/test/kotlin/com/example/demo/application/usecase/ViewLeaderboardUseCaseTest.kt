@file:Suppress("NonAsciiCharacters")

package com.example.demo.application.usecase

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ViewLeaderboardUseCaseTest {
    @Autowired
    lateinit var target: ViewLeaderboardUseCase

    @Test
    fun view_ランキング生成の確認() {
        runBlocking {
            val actual = target.view()
            assertEquals(
                "[{\"rank\":1,\"playerId\":\"asgqr_grg09w\",\"handleName\":\"player4\",\"score\":600},{\"rank\":2,\"playerId\":\"sfew__svdsfe\",\"handleName\":\"player10\",\"score\":550},{\"rank\":3,\"playerId\":\"134534t09ud0\",\"handleName\":\"player7\",\"score\":370},{\"rank\":4,\"playerId\":\"fmwqor49u5af\",\"handleName\":\"player3\",\"score\":350},{\"rank\":4,\"playerId\":\"gno4328rsd_f\",\"handleName\":\"player9\",\"score\":350},{\"rank\":5,\"playerId\":\"amort8u3450t\",\"handleName\":\"player2\",\"score\":300},{\"rank\":5,\"playerId\":\"vmer0rt9ejrd\",\"handleName\":\"player8\",\"score\":300},{\"rank\":6,\"playerId\":\"MSEOgwMP_0t\",\"handleName\":\"player12\",\"score\":100},{\"rank\":6,\"playerId\":\"gm4oy9jfqems\",\"handleName\":\"player11\",\"score\":100},{\"rank\":6,\"playerId\":\"nrogh923u50sj\",\"handleName\":\"player1\",\"score\":100}]",
                actual
            )
        }
    }
}