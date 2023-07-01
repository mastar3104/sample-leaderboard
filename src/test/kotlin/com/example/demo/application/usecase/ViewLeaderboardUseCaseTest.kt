package com.example.demo.application.usecase

import com.example.demo.domain.entity.Leaderboard
import com.example.demo.domain.model.LeaderboardDetail
import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
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
    fun `view_リーダーボード生成の確認`() {
        runBlocking {
            val actual = target.view()
            assertEquals(
                Leaderboard(
                    _list = listOf(
                        LeaderboardDetail(
                            rank = 1,
                            playerId = PlayerId(value = "asgqr_grg09w"),
                            handleName = HandleName(value = "player4"),
                            score = Score(value = 600)
                        ), LeaderboardDetail(
                            rank = 2,
                            playerId = PlayerId(value = "sfew__svdsfe"),
                            handleName = HandleName(value = "player10"),
                            score = Score(value = 550)
                        ), LeaderboardDetail(
                            rank = 3,
                            playerId = PlayerId(value = "134534t09ud0"),
                            handleName = HandleName(value = "player7"),
                            score = Score(value = 370)
                        ), LeaderboardDetail(
                            rank = 4,
                            playerId = PlayerId(value = "fmwqor49u5af"),
                            handleName = HandleName(value = "player3"),
                            score = Score(value = 350)
                        ), LeaderboardDetail(
                            rank = 4,
                            playerId = PlayerId(value = "gno4328rsd_f"),
                            handleName = HandleName(value = "player9"),
                            score = Score(value = 350)
                        ), LeaderboardDetail(
                            rank = 5,
                            playerId = PlayerId(value = "amort8u3450t"),
                            handleName = HandleName(value = "player2"),
                            score = Score(value = 300)
                        ), LeaderboardDetail(
                            rank = 5,
                            playerId = PlayerId(value = "vmer0rt9ejrd"),
                            handleName = HandleName(value = "player8"),
                            score = Score(value = 300)
                        ), LeaderboardDetail(
                            rank = 6,
                            playerId = PlayerId(value = "nrogh923u50sj"),
                            handleName = HandleName(value = "player1"),
                            score = Score(value = 100)
                        ), LeaderboardDetail(
                            rank = 6,
                            playerId = PlayerId(value = "gm4oy9jfqems"),
                            handleName = HandleName(value = "player11"),
                            score = Score(value = 100)
                        ), LeaderboardDetail(
                            rank = 6,
                            playerId = PlayerId(value = "MSEOgwMP_0t"),
                            handleName = HandleName(value = "player12"),
                            score = Score(value = 100)
                        ), LeaderboardDetail(
                            rank = 7,
                            playerId = PlayerId(value = "ptqs_r90tegs"),
                            handleName = HandleName(value = "player5"),
                            score = Score(value = 50)
                        ), LeaderboardDetail(
                            rank = 7,
                            playerId = PlayerId(value = "sfpe695ejw_q"),
                            handleName = HandleName(value = "player6"),
                            score = Score(value = 50)
                        )
                    )
                ), actual
            )
        }
    }
}