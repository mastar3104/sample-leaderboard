package com.example.demo.domain.service

import com.example.demo.domain.collection.GameScoreList
import com.example.demo.domain.collection.PlayerList
import com.example.demo.domain.entity.GameScore
import com.example.demo.domain.model.LeaderboardDetail
import com.example.demo.domain.entity.Player
import com.example.demo.domain.entity.Leaderboard
import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.Instant

class LeaderboardServiceTest {
    @ParameterizedTest
    @MethodSource("com.example.demo.domain.service.LeaderboardServiceTestKt#createParam")
    fun `create_プレイヤー情報とスコア情報を元にリーダーボードを生成する`(title: String, playerList: PlayerList, gameScoreList: GameScoreList, expected: Leaderboard) {
        val actual = LeaderboardService.create(playerList, gameScoreList)
        assertEquals(expected, actual)
    }
}

fun createParam() = listOf(
    arguments(
        "Scoreの降順にRankを振られ、PlayerIdに紐づくPlayer情報と突合してLeaderboardが生成されること",
        PlayerList(listOf(
            Player(PlayerId("01"),  HandleName("test01")),
            Player(PlayerId("02"),  HandleName("test02")),
            Player(PlayerId("03"),  HandleName("test03")),
        )),
        GameScoreList(listOf(
            GameScore(PlayerId("01"), Score(100), Instant.MIN,),
            GameScore(PlayerId("02"), Score(200), Instant.MIN,),
            GameScore(PlayerId("03"), Score(300), Instant.MIN,),
        )),
        Leaderboard(listOf(
            LeaderboardDetail(1, PlayerId("03"),  HandleName("test03"), Score(300)),
            LeaderboardDetail(2, PlayerId("02"),  HandleName("test02"), Score(200)),
            LeaderboardDetail(3, PlayerId("01"),  HandleName("test01"), Score(100)),
        ))
    ),
    arguments(
        "GameScoreListに同一playerIdが複数存在する場合、Scoreが高いレコードのみを参照してLeaderboardが生成されること",
        PlayerList(listOf(
            Player(PlayerId("01"),  HandleName("test01")),
            Player(PlayerId("02"),  HandleName("test02")),
            Player(PlayerId("03"),  HandleName("test03")),
        )),
        GameScoreList(listOf(
            GameScore(PlayerId("01"), Score(100), Instant.MIN,),
            GameScore(PlayerId("01"), Score(200), Instant.MIN,),
            GameScore(PlayerId("01"), Score(300), Instant.MIN,),
            GameScore(PlayerId("02"), Score(200), Instant.MIN,),
            GameScore(PlayerId("02"), Score(200), Instant.MIN,),
            GameScore(PlayerId("03"), Score(600), Instant.MIN,),
            GameScore(PlayerId("03"), Score(300), Instant.MIN,),
        )),
        Leaderboard(listOf(
            LeaderboardDetail(1, PlayerId("03"),  HandleName("test03"), Score(600)),
            LeaderboardDetail(2, PlayerId("01"),  HandleName("test01"), Score(300)),
            LeaderboardDetail(3, PlayerId("02"),  HandleName("test02"), Score(200)),
        ))
    ),
    arguments(
        "同一ScoreのPlayerが存在する場合、同じRankを振ること。また、後続のRankはScoreが重複した数だけ飛び番とすること",
        PlayerList(listOf(
            Player(PlayerId("01"),  HandleName("test01")),
            Player(PlayerId("02"),  HandleName("test02")),
            Player(PlayerId("03"),  HandleName("test03")),
            Player(PlayerId("04"),  HandleName("test04")),
            Player(PlayerId("05"),  HandleName("test05")),
            Player(PlayerId("06"),  HandleName("test06")),
            Player(PlayerId("07"),  HandleName("test07")),
            Player(PlayerId("08"),  HandleName("test08")),
            Player(PlayerId("09"),  HandleName("test09")),
            Player(PlayerId("10"),  HandleName("test10")),
            Player(PlayerId("11"),  HandleName("test11")),
            Player(PlayerId("12"),  HandleName("test12")),
        )),
        GameScoreList(listOf(
            GameScore(PlayerId("01"), Score(100), Instant.MIN,),
            GameScore(PlayerId("02"), Score(200), Instant.MIN,),
            GameScore(PlayerId("03"), Score(300), Instant.MIN,),
            GameScore(PlayerId("03"), Score(400), Instant.MIN,),
            GameScore(PlayerId("04"), Score(300), Instant.MIN,),
            GameScore(PlayerId("05"), Score(200), Instant.MIN,),
            GameScore(PlayerId("06"), Score(100), Instant.MIN,),
            GameScore(PlayerId("07"), Score(200), Instant.MIN,),
            GameScore(PlayerId("08"), Score(300), Instant.MIN,),
            GameScore(PlayerId("09"), Score(400), Instant.MIN,),
            GameScore(PlayerId("10"), Score(500), Instant.MIN,),
            GameScore(PlayerId("11"), Score(600), Instant.MIN,),
            GameScore(PlayerId("12"), Score(700), Instant.MIN,),
        )),
        Leaderboard(listOf(
            LeaderboardDetail(1, PlayerId("12"),  HandleName("test12"), Score(700)),
            LeaderboardDetail(2, PlayerId("11"),  HandleName("test11"), Score(600)),
            LeaderboardDetail(3, PlayerId("10"),  HandleName("test10"), Score(500)),
            LeaderboardDetail(4, PlayerId("03"),  HandleName("test03"), Score(400)),
            LeaderboardDetail(4, PlayerId("09"),  HandleName("test09"), Score(400)),
            LeaderboardDetail(5, PlayerId("04"),  HandleName("test04"), Score(300)),
            LeaderboardDetail(5, PlayerId("08"),  HandleName("test08"), Score(300)),
            LeaderboardDetail(6, PlayerId("02"),  HandleName("test02"), Score(200)),
            LeaderboardDetail(6, PlayerId("05"),  HandleName("test05"), Score(200)),
            LeaderboardDetail(6, PlayerId("07"),  HandleName("test07"), Score(200)),
            LeaderboardDetail(7, PlayerId("01"),  HandleName("test01"), Score(100)),
            LeaderboardDetail(7, PlayerId("06"),  HandleName("test06"), Score(100)),
        ))
    ),
)