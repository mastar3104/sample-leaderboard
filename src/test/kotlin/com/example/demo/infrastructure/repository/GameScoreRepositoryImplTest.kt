package com.example.demo.infrastructure.repository

import com.example.demo.domain.collection.GameScoreList
import com.example.demo.domain.entity.GameScore
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.time.OffsetDateTime

@SpringBootTest
class GameScoreRepositoryImplTest {
    @Autowired
    lateinit var gameScoreRepository: GameScoreRepositoryImpl

    @Test
    fun `get_csvのマッピングができること`() {
        runBlocking {
            val actual = gameScoreRepository.get()
            assertEquals(GameScoreList(listOf(
                GameScore(
                    playerId = PlayerId("nrogh923u50sj"),
                    score = Score(100),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("amort8u3450t"),
                    score = Score(300),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("fmwqor49u5af"),
                    score = Score(300),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("asgqr_grg09w"),
                    score = Score(600),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("fmwqor49u5af"),
                    score = Score(350),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("ptqs_r90tegs"),
                    score = Score(50),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("sfpe695ejw_q"),
                    score = Score(50),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("134534t09ud0"),
                    score = Score(370),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("vmer0rt9ejrd"),
                    score = Score(300),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("gno4328rsd_f"),
                    score = Score(350),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("sfew__svdsfe"),
                    score = Score(550),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("gno4328rsd_f"),
                    score = Score(100),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("gm4oy9jfqems"),
                    score = Score(100),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
                GameScore(
                    playerId = PlayerId("MSEOgwMP_0t"),
                    score = Score(100),
                    createTimestamp = LocalDateTime.of(
                        2023, 1, 1, 12, 59, 59
                    ).toInstant(OffsetDateTime.now().offset)
                ),
            )), actual)
        }
    }
}