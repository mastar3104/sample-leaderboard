package com.example.demo.domain.collection

import com.example.demo.domain.entity.GameScore
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.Instant

class GameScoreListTest {

    @ParameterizedTest
    @MethodSource("com.example.demo.domain.collection.GameScoreListTestKt#sortedByScoreAndPlayerIdParam")
    fun `sortedByScoreDesc_Scoreの降順にソートされること`(title: String, target: List<GameScore>, expected: List<GameScore>) {
        assertEquals(GameScoreList(expected), GameScoreList(target).sortedByScoreDesc)
    }

    @ParameterizedTest
    @MethodSource("com.example.demo.domain.collection.GameScoreListTestKt#distinctByPlayerIdParam")
    fun `distinctByPlayerId_PlayerIdの重複を排除する`(title: String, target: List<GameScore>, expected: List<GameScore>) {
        assertEquals(GameScoreList(expected), GameScoreList(target).distinctByPlayerId)
    }
}

fun sortedByScoreAndPlayerIdParam() = listOf(
    arguments(
        "スコアの昇順の場合",
        listOf(
            GameScore(
                score = Score(50),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test4")
            ),
            GameScore(
                score = Score(100),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test2")
            ),
            GameScore(
                score = Score(100),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test3")
            ),
            GameScore(
                score = Score(300),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test1")
            ),
        ),
        listOf(
            GameScore(
                score = Score(300),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test1")
            ),
            GameScore(
                score = Score(100),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test2")
            ),
            GameScore(
                score = Score(100),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test3")
            ),
            GameScore(
                score = Score(50),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test4")
            ),
        ),
    ),
    arguments(
        "同じスコアが存在する場合",
        listOf(
            GameScore(
                score = Score(100),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test2")
            ),
            GameScore(
                score = Score(100),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test3")
            ),
            GameScore(
                score = Score(50),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test4")
            ),
            GameScore(
                score = Score(300),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test1")
            ),
        ),
        listOf(
            GameScore(
                score = Score(300),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test1")
            ),
            GameScore(
                score = Score(100),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test2")
            ),
            GameScore(
                score = Score(100),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test3")
            ),
            GameScore(
                score = Score(50),
                createTimestamp = Instant.MAX,
                playerId = PlayerId("test4")
            ),
        ),
    ),
    arguments(
        "配列が空の場合",
        emptyList<GameScore>(),
        emptyList<GameScore>(),
    )
)

fun distinctByPlayerIdParam() = listOf(
    arguments(
        "重複あり",
        listOf(
            GameScore(
                playerId = PlayerId("01"),
                score = Score(100),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("01"),
                score = Score(200),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("02"),
                score = Score(1000),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("02"),
                score = Score(200),
                createTimestamp = Instant.MIN
            ),
        ),
        listOf(
            GameScore(
                playerId = PlayerId("01"),
                score = Score(100),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("02"),
                score = Score(1000),
                createTimestamp = Instant.MIN
            ),
        ),
    ),
    arguments(
        "重複なし",
        listOf(
            GameScore(
                playerId = PlayerId("01"),
                score = Score(100),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("02"),
                score = Score(200),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("03"),
                score = Score(1000),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("04"),
                score = Score(200),
                createTimestamp = Instant.MIN
            ),
        ),
        listOf(
            GameScore(
                playerId = PlayerId("01"),
                score = Score(100),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("02"),
                score = Score(200),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("03"),
                score = Score(1000),
                createTimestamp = Instant.MIN
            ),
            GameScore(
                playerId = PlayerId("04"),
                score = Score(200),
                createTimestamp = Instant.MIN
            ),
        ),
    ),
    arguments(
        "配列が空の場合",
        emptyList<GameScore>(),
        emptyList<GameScore>(),
    )
)