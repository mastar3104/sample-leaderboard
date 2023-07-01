package com.example.demo.domain.entity

import com.example.demo.domain.model.LeaderboardDetail
import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class LeaderboardTest {

    @ParameterizedTest
    @MethodSource("com.example.demo.domain.entity.LeaderboardTestKt#viewListParam")
    fun `viewList_リーダーボードの描画ルールに則った順位のlistを生成する`(
        title: String,
        list: List<LeaderboardDetail>,
        expected: List<LeaderboardDetail>
    ) {
        val target = Leaderboard(list)
        assertEquals(expected, target.viewList)
    }

    @ParameterizedTest
    @MethodSource("com.example.demo.domain.entity.LeaderboardTestKt#initParam")
    fun `init_PlayerIdが重複したLeaderboardの生成を許容しない`(
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

fun viewListParam() = listOf(
    arguments(
        "rankの昇順,playerIdの昇順に表示されること",
        listOf(
            LeaderboardDetail(4, PlayerId("4"), HandleName("test4"), Score(200)),
            LeaderboardDetail(5, PlayerId("6"), HandleName("test6"), Score(100)),
            LeaderboardDetail(5, PlayerId("5"), HandleName("test5"), Score(100)),
            LeaderboardDetail(3, PlayerId("3"), HandleName("test3"), Score(300)),
            LeaderboardDetail(2, PlayerId("2"), HandleName("test2"), Score(400)),
            LeaderboardDetail(1, PlayerId("1"), HandleName("test1"), Score(500)),
            LeaderboardDetail(1, PlayerId("0"), HandleName("test0"), Score(500)),
        ),
        listOf(
            LeaderboardDetail(1, PlayerId("0"), HandleName("test0"), Score(500)),
            LeaderboardDetail(1, PlayerId("1"), HandleName("test1"), Score(500)),
            LeaderboardDetail(2, PlayerId("2"), HandleName("test2"), Score(400)),
            LeaderboardDetail(3, PlayerId("3"), HandleName("test3"), Score(300)),
            LeaderboardDetail(4, PlayerId("4"), HandleName("test4"), Score(200)),
            LeaderboardDetail(5, PlayerId("5"), HandleName("test5"), Score(100)),
            LeaderboardDetail(5, PlayerId("6"), HandleName("test6"), Score(100)),
        ),
    ),
    arguments(
        "10件しか存在しない場合、全て表示されること",
        listOf(
            LeaderboardDetail(1, PlayerId("10"), HandleName("test10"), Score(1000)),
            LeaderboardDetail(2, PlayerId("9"), HandleName("test09"), Score(900)),
            LeaderboardDetail(3, PlayerId("8"), HandleName("test08"), Score(800)),
            LeaderboardDetail(4, PlayerId("7"), HandleName("test07"), Score(700)),
            LeaderboardDetail(5, PlayerId("6"), HandleName("test06"), Score(600)),
            LeaderboardDetail(6, PlayerId("5"), HandleName("test05"), Score(500)),
            LeaderboardDetail(7, PlayerId("4"), HandleName("test04"), Score(400)),
            LeaderboardDetail(8, PlayerId("3"), HandleName("test03"), Score(300)),
            LeaderboardDetail(9, PlayerId("2"), HandleName("test02"), Score(200)),
            LeaderboardDetail(10, PlayerId("1"), HandleName("test01"), Score(100)),
        ),
        listOf(
            LeaderboardDetail(1, PlayerId("10"), HandleName("test10"), Score(1000)),
            LeaderboardDetail(2, PlayerId("9"), HandleName("test09"), Score(900)),
            LeaderboardDetail(3, PlayerId("8"), HandleName("test08"), Score(800)),
            LeaderboardDetail(4, PlayerId("7"), HandleName("test07"), Score(700)),
            LeaderboardDetail(5, PlayerId("6"), HandleName("test06"), Score(600)),
            LeaderboardDetail(6, PlayerId("5"), HandleName("test05"), Score(500)),
            LeaderboardDetail(7, PlayerId("4"), HandleName("test04"), Score(400)),
            LeaderboardDetail(8, PlayerId("3"), HandleName("test03"), Score(300)),
            LeaderboardDetail(9, PlayerId("2"), HandleName("test02"), Score(200)),
            LeaderboardDetail(10, PlayerId("1"), HandleName("test01"), Score(100)),
        ),
    ),
    arguments(
        "ランキング重複なしで11件存在する場合、rank=10まで表示されること",
        listOf(
            LeaderboardDetail(1, PlayerId("10"), HandleName("test10"), Score(1000)),
            LeaderboardDetail(2, PlayerId("09"), HandleName("test09"), Score(900)),
            LeaderboardDetail(3, PlayerId("08"), HandleName("test08"), Score(800)),
            LeaderboardDetail(4, PlayerId("07"), HandleName("test07"), Score(700)),
            LeaderboardDetail(5, PlayerId("06"), HandleName("test06"), Score(600)),
            LeaderboardDetail(6, PlayerId("05"), HandleName("test05"), Score(500)),
            LeaderboardDetail(7, PlayerId("04"), HandleName("test04"), Score(400)),
            LeaderboardDetail(8, PlayerId("03"), HandleName("test03"), Score(300)),
            LeaderboardDetail(9, PlayerId("02"), HandleName("test02"), Score(200)),
            LeaderboardDetail(10, PlayerId("01"), HandleName("test01"), Score(100)),
            LeaderboardDetail(11, PlayerId("11"), HandleName("test11"), Score(50)),
        ),
        listOf(
            LeaderboardDetail(1, PlayerId("10"), HandleName("test10"), Score(1000)),
            LeaderboardDetail(2, PlayerId("09"), HandleName("test09"), Score(900)),
            LeaderboardDetail(3, PlayerId("08"), HandleName("test08"), Score(800)),
            LeaderboardDetail(4, PlayerId("07"), HandleName("test07"), Score(700)),
            LeaderboardDetail(5, PlayerId("06"), HandleName("test06"), Score(600)),
            LeaderboardDetail(6, PlayerId("05"), HandleName("test05"), Score(500)),
            LeaderboardDetail(7, PlayerId("04"), HandleName("test04"), Score(400)),
            LeaderboardDetail(8, PlayerId("03"), HandleName("test03"), Score(300)),
            LeaderboardDetail(9, PlayerId("02"), HandleName("test02"), Score(200)),
            LeaderboardDetail(10, PlayerId("01"), HandleName("test01"), Score(100)),
        ),
    ),
    arguments(
        "ランキング重複件ありで10件目のrankが9である場合、rank9まで表示すること",
        listOf(
            LeaderboardDetail(1, PlayerId("01"), HandleName("test01"), Score(1000)),
            LeaderboardDetail(2, PlayerId("02"), HandleName("test02"), Score(900)),
            LeaderboardDetail(3, PlayerId("03"), HandleName("test03"), Score(800)),
            LeaderboardDetail(4, PlayerId("04"), HandleName("test04"), Score(700)),
            LeaderboardDetail(4, PlayerId("05"), HandleName("test05"), Score(700)),
            LeaderboardDetail(6, PlayerId("06"), HandleName("test06"), Score(500)),
            LeaderboardDetail(7, PlayerId("07"), HandleName("test07"), Score(400)),
            LeaderboardDetail(7, PlayerId("08"), HandleName("test08"), Score(400)),
            LeaderboardDetail(9, PlayerId("09"), HandleName("test09"), Score(200)),
            LeaderboardDetail(9, PlayerId("10"), HandleName("test10"), Score(200)),
            LeaderboardDetail(9, PlayerId("11"), HandleName("test11"), Score(200)),
            LeaderboardDetail(10, PlayerId("12"), HandleName("test12"), Score(100)),
            LeaderboardDetail(11, PlayerId("13"), HandleName("test13"), Score(50)),
            LeaderboardDetail(12, PlayerId("14"), HandleName("test14"), Score(25)),
        ),
        listOf(
            LeaderboardDetail(1, PlayerId("01"), HandleName("test01"), Score(1000)),
            LeaderboardDetail(2, PlayerId("02"), HandleName("test02"), Score(900)),
            LeaderboardDetail(3, PlayerId("03"), HandleName("test03"), Score(800)),
            LeaderboardDetail(4, PlayerId("04"), HandleName("test04"), Score(700)),
            LeaderboardDetail(4, PlayerId("05"), HandleName("test05"), Score(700)),
            LeaderboardDetail(6, PlayerId("06"), HandleName("test06"), Score(500)),
            LeaderboardDetail(7, PlayerId("07"), HandleName("test07"), Score(400)),
            LeaderboardDetail(7, PlayerId("08"), HandleName("test08"), Score(400)),
            LeaderboardDetail(9, PlayerId("09"), HandleName("test09"), Score(200)),
            LeaderboardDetail(9, PlayerId("10"), HandleName("test10"), Score(200)),
            LeaderboardDetail(9, PlayerId("11"), HandleName("test11"), Score(200)),
        ),
    ),
    arguments(
        "ランキング重複件ありで10件目のrankが7である場合、rank7まで表示すること",
        listOf(
            LeaderboardDetail(1, PlayerId("01"), HandleName("test01"), Score(1000)),
            LeaderboardDetail(2, PlayerId("02"), HandleName("test02"), Score(900)),
            LeaderboardDetail(3, PlayerId("03"), HandleName("test03"), Score(800)),
            LeaderboardDetail(4, PlayerId("04"), HandleName("test04"), Score(700)),
            LeaderboardDetail(4, PlayerId("05"), HandleName("test05"), Score(700)),
            LeaderboardDetail(6, PlayerId("06"), HandleName("test06"), Score(500)),
            LeaderboardDetail(7, PlayerId("07"), HandleName("test07"), Score(400)),
            LeaderboardDetail(7, PlayerId("08"), HandleName("test08"), Score(400)),
            LeaderboardDetail(7, PlayerId("09"), HandleName("test09"), Score(400)),
            LeaderboardDetail(7, PlayerId("10"), HandleName("test10"), Score(400)),
            LeaderboardDetail(7, PlayerId("11"), HandleName("test11"), Score(400)),
            LeaderboardDetail(10, PlayerId("12"), HandleName("test12"), Score(100)),
            LeaderboardDetail(11, PlayerId("13"), HandleName("test13"), Score(50)),
            LeaderboardDetail(12, PlayerId("14"), HandleName("test14"), Score(25)),
        ),
        listOf(
            LeaderboardDetail(1, PlayerId("01"), HandleName("test01"), Score(1000)),
            LeaderboardDetail(2, PlayerId("02"), HandleName("test02"), Score(900)),
            LeaderboardDetail(3, PlayerId("03"), HandleName("test03"), Score(800)),
            LeaderboardDetail(4, PlayerId("04"), HandleName("test04"), Score(700)),
            LeaderboardDetail(4, PlayerId("05"), HandleName("test05"), Score(700)),
            LeaderboardDetail(6, PlayerId("06"), HandleName("test06"), Score(500)),
            LeaderboardDetail(7, PlayerId("07"), HandleName("test07"), Score(400)),
            LeaderboardDetail(7, PlayerId("08"), HandleName("test08"), Score(400)),
            LeaderboardDetail(7, PlayerId("09"), HandleName("test09"), Score(400)),
            LeaderboardDetail(7, PlayerId("10"), HandleName("test10"), Score(400)),
            LeaderboardDetail(7, PlayerId("11"), HandleName("test11"), Score(400)),
        ),
    ),
)

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

