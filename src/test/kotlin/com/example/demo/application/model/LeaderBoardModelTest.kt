@file:Suppress("NonAsciiCharacters")

package com.example.demo.application.model

import com.example.demo.application.view.LeaderboardDetail
import com.example.demo.domain.entity.Leaderboard
import com.example.demo.domain.model.LeaderboardDetail as DomainLeaderboardDetail
import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource


class LeaderBoardModelTest {

    @ParameterizedTest
    @MethodSource("com.example.demo.application.model.LeaderBoardModelTestKt#viewListParam")
    fun viewList_リーダーボードの描画ルールに則った順位のlistを生成する(
        title: String,
        list: List<DomainLeaderboardDetail>,
        expected: List<LeaderboardDetail>
    ) {
        val target = LeaderBoardModel(Leaderboard(list))
        Assertions.assertEquals(expected, target.viewList)
    }
}

fun viewListParam() = listOf(
    Arguments.arguments(
        "rankの昇順,playerIdの昇順に表示されること",
        listOf(
            DomainLeaderboardDetail(4, PlayerId("4"), HandleName("test4"), Score(200)),
            DomainLeaderboardDetail(5, PlayerId("6"), HandleName("test6"), Score(100)),
            DomainLeaderboardDetail(5, PlayerId("5"), HandleName("test5"), Score(100)),
            DomainLeaderboardDetail(3, PlayerId("3"), HandleName("test3"), Score(300)),
            DomainLeaderboardDetail(2, PlayerId("2"), HandleName("test2"), Score(400)),
            DomainLeaderboardDetail(1, PlayerId("1"), HandleName("test1"), Score(500)),
            DomainLeaderboardDetail(1, PlayerId("0"), HandleName("test0"), Score(500)),
        ),
        listOf(
            LeaderboardDetail(1, "0", "test0", 500),
            LeaderboardDetail(1, "1", "test1", 500),
            LeaderboardDetail(2, "2", "test2", 400),
            LeaderboardDetail(3, "3", "test3", 300),
            LeaderboardDetail(4, "4", "test4", 200),
            LeaderboardDetail(5, "5", "test5", 100),
            LeaderboardDetail(5, "6", "test6", 100),
        ),
    ),
    Arguments.arguments(
        "10件しか存在しない場合、全て表示されること",
        listOf(
            DomainLeaderboardDetail(1, PlayerId("10"), HandleName("test10"), Score(1000)),
            DomainLeaderboardDetail(2, PlayerId("9"), HandleName("test09"), Score(900)),
            DomainLeaderboardDetail(3, PlayerId("8"), HandleName("test08"), Score(800)),
            DomainLeaderboardDetail(4, PlayerId("7"), HandleName("test07"), Score(700)),
            DomainLeaderboardDetail(5, PlayerId("6"), HandleName("test06"), Score(600)),
            DomainLeaderboardDetail(6, PlayerId("5"), HandleName("test05"), Score(500)),
            DomainLeaderboardDetail(7, PlayerId("4"), HandleName("test04"), Score(400)),
            DomainLeaderboardDetail(8, PlayerId("3"), HandleName("test03"), Score(300)),
            DomainLeaderboardDetail(9, PlayerId("2"), HandleName("test02"), Score(200)),
            DomainLeaderboardDetail(10, PlayerId("1"), HandleName("test01"), Score(100)),
        ),
        listOf(
            LeaderboardDetail(1,"10", "test10",1000),
            LeaderboardDetail(2,"9", "test09",900),
            LeaderboardDetail(3,"8", "test08",800),
            LeaderboardDetail(4,"7", "test07" ,700),
            LeaderboardDetail(5,"6", "test06" ,600),
            LeaderboardDetail(6,"5", "test05" ,500),
            LeaderboardDetail(7,"4", "test04" ,400),
            LeaderboardDetail(8,"3", "test03" ,300),
            LeaderboardDetail(9,"2", "test02" ,200),
            LeaderboardDetail(10,"1", "test01", 100),
        ),
    ),
    Arguments.arguments(
        "ランキング重複なしで11件存在する場合、rank=10まで表示されること",
        listOf(
            DomainLeaderboardDetail(1, PlayerId("10"), HandleName("test10"), Score(1000)),
            DomainLeaderboardDetail(2, PlayerId("09"), HandleName("test09"), Score(900)),
            DomainLeaderboardDetail(3, PlayerId("08"), HandleName("test08"), Score(800)),
            DomainLeaderboardDetail(4, PlayerId("07"), HandleName("test07"), Score(700)),
            DomainLeaderboardDetail(5, PlayerId("06"), HandleName("test06"), Score(600)),
            DomainLeaderboardDetail(6, PlayerId("05"), HandleName("test05"), Score(500)),
            DomainLeaderboardDetail(7, PlayerId("04"), HandleName("test04"), Score(400)),
            DomainLeaderboardDetail(8, PlayerId("03"), HandleName("test03"), Score(300)),
            DomainLeaderboardDetail(9, PlayerId("02"), HandleName("test02"), Score(200)),
            DomainLeaderboardDetail(10, PlayerId("01"), HandleName("test01"), Score(100)),
            DomainLeaderboardDetail(11, PlayerId("11"), HandleName("test11"), Score(50)),
        ),
        listOf(
            LeaderboardDetail(1,"10","test10",1000),
            LeaderboardDetail(2,"09","test09",900),
            LeaderboardDetail(3,"08","test08",800),
            LeaderboardDetail(4,"07","test07",700),
            LeaderboardDetail(5,"06","test06",600),
            LeaderboardDetail(6,"05","test05",500),
            LeaderboardDetail(7,"04","test04",400),
            LeaderboardDetail(8,"03","test03",300),
            LeaderboardDetail(9,"02","test02",200),
            LeaderboardDetail(10,"01","test01",100),
        ),
    ),
    Arguments.arguments(
        "ランキング重複件ありで10件目のrankが9である場合、rank9まで表示すること",
        listOf(
            DomainLeaderboardDetail(1, PlayerId("01"), HandleName("test01"), Score(1000)),
            DomainLeaderboardDetail(2, PlayerId("02"), HandleName("test02"), Score(900)),
            DomainLeaderboardDetail(3, PlayerId("03"), HandleName("test03"), Score(800)),
            DomainLeaderboardDetail(4, PlayerId("04"), HandleName("test04"), Score(700)),
            DomainLeaderboardDetail(4, PlayerId("05"), HandleName("test05"), Score(700)),
            DomainLeaderboardDetail(6, PlayerId("06"), HandleName("test06"), Score(500)),
            DomainLeaderboardDetail(7, PlayerId("07"), HandleName("test07"), Score(400)),
            DomainLeaderboardDetail(7, PlayerId("08"), HandleName("test08"), Score(400)),
            DomainLeaderboardDetail(9, PlayerId("09"), HandleName("test09"), Score(200)),
            DomainLeaderboardDetail(9, PlayerId("10"), HandleName("test10"), Score(200)),
            DomainLeaderboardDetail(9, PlayerId("11"), HandleName("test11"), Score(200)),
            DomainLeaderboardDetail(10, PlayerId("12"), HandleName("test12"), Score(100)),
            DomainLeaderboardDetail(11, PlayerId("13"), HandleName("test13"), Score(50)),
            DomainLeaderboardDetail(12, PlayerId("14"), HandleName("test14"), Score(25)),
        ),
        listOf(
            LeaderboardDetail(1,"01", "test01",1000),
            LeaderboardDetail(2,"02", "test02",900),
            LeaderboardDetail(3,"03", "test03",800),
            LeaderboardDetail(4,"04", "test04",700),
            LeaderboardDetail(4,"05", "test05",700),
            LeaderboardDetail(6,"06", "test06",500),
            LeaderboardDetail(7,"07", "test07",400),
            LeaderboardDetail(7,"08", "test08",400),
            LeaderboardDetail(9,"09", "test09",200),
            LeaderboardDetail(9,"10", "test10",200),
            LeaderboardDetail(9,"11", "test11",200),
        ),
    ),
    Arguments.arguments(
        "ランキング重複件ありで10件目のrankが7である場合、rank7まで表示すること",
        listOf(
            DomainLeaderboardDetail(1, PlayerId("01"), HandleName("test01"), Score(1000)),
            DomainLeaderboardDetail(2, PlayerId("02"), HandleName("test02"), Score(900)),
            DomainLeaderboardDetail(3, PlayerId("03"), HandleName("test03"), Score(800)),
            DomainLeaderboardDetail(4, PlayerId("04"), HandleName("test04"), Score(700)),
            DomainLeaderboardDetail(4, PlayerId("05"), HandleName("test05"), Score(700)),
            DomainLeaderboardDetail(6, PlayerId("06"), HandleName("test06"), Score(500)),
            DomainLeaderboardDetail(7, PlayerId("07"), HandleName("test07"), Score(400)),
            DomainLeaderboardDetail(7, PlayerId("08"), HandleName("test08"), Score(400)),
            DomainLeaderboardDetail(7, PlayerId("09"), HandleName("test09"), Score(400)),
            DomainLeaderboardDetail(7, PlayerId("10"), HandleName("test10"), Score(400)),
            DomainLeaderboardDetail(7, PlayerId("11"), HandleName("test11"), Score(400)),
            DomainLeaderboardDetail(10, PlayerId("12"), HandleName("test12"), Score(100)),
            DomainLeaderboardDetail(11, PlayerId("13"), HandleName("test13"), Score(50)),
            DomainLeaderboardDetail(12, PlayerId("14"), HandleName("test14"), Score(25)),
        ),
        listOf(
            LeaderboardDetail(1,"01","test01",1000),
            LeaderboardDetail(2,"02","test02",900),
            LeaderboardDetail(3,"03","test03",800),
            LeaderboardDetail(4,"04","test04",700),
            LeaderboardDetail(4,"05","test05",700),
            LeaderboardDetail(6,"06","test06",500),
            LeaderboardDetail(7,"07","test07",400),
            LeaderboardDetail(7,"08","test08",400),
            LeaderboardDetail(7,"09","test09",400),
            LeaderboardDetail(7,"10","test10",400),
            LeaderboardDetail(7,"11","test11",400),
        ),
    ),
)
