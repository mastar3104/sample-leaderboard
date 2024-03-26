@file:Suppress("NonAsciiCharacters")

package com.example.demo.domain.entity

import com.example.demo.domain.model.LeaderboardDetail
import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class LeaderboardTest {
    @ParameterizedTest
    @MethodSource("com.example.demo.domain.entity.LeaderboardTestKt#initParam")
    fun init_PlayerIdが重複したLeaderboardの生成を許容しない(
        title: String,
        list: List<LeaderboardDetail>,
        throwable: Boolean
    ) {
        if (throwable) {
            assertThrows<IllegalArgumentException> {
                Leaderboard(list)
            }
        } else {
            Leaderboard(list)
        }
    }
}

fun initParam() = listOf(
    arguments(
        "playerId重複が存在しない場合",
        listOf(
            LeaderboardDetail(1, PlayerId("1"), HandleName("test1"), Score(100)),
            LeaderboardDetail(2, PlayerId("2"), HandleName("test2"), Score(200)),
            LeaderboardDetail(3, PlayerId("3"), HandleName("test3"), Score(300)),
            LeaderboardDetail(4, PlayerId("4"), HandleName("test4"), Score(400)),
            LeaderboardDetail(5, PlayerId("5"), HandleName("test5"), Score(500)),
        ),
        false,
    ),
    arguments(
        "playerId重複が存在する場合",
        listOf(
            LeaderboardDetail(1, PlayerId("1"), HandleName("test1"), Score(100)),
            LeaderboardDetail(2, PlayerId("2"), HandleName("test2"), Score(200)),
            LeaderboardDetail(3, PlayerId("2"), HandleName("test2"), Score(300)),
            LeaderboardDetail(4, PlayerId("4"), HandleName("test4"), Score(400)),
            LeaderboardDetail(5, PlayerId("5"), HandleName("test5"), Score(500)),
        ),
        true,
    )
)

