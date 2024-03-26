package com.example.demo.application.model

import com.example.demo.domain.entity.Leaderboard
import com.example.demo.application.view.LeaderboardDetail

class LeaderBoardModel(
    private val leaderboard: Leaderboard,
) {
    val viewList: List<LeaderboardDetail>
        get() {
            val resultList = mutableListOf<LeaderboardDetail>()
            val sortedList = leaderboard.detailList.sortedBy { it.playerId }.sortedBy { it.rank }
            run loop@ {
                sortedList.forEachIndexed { index, leaderboardDetail ->
                    sortedList.getOrNull(index - 1)?.let { prevLeaderboardDetail ->
                        if (leaderboardDetail.rank == prevLeaderboardDetail.rank) {
                            resultList.add(LeaderboardDetail(
                                rank = leaderboardDetail.rank,
                                playerId = leaderboardDetail.playerId.toString(),
                                handleName = leaderboardDetail.handleName.toString(),
                                score = leaderboardDetail.score.toInt(),
                            ))
                            return@forEachIndexed
                        }
                    }
                    if (leaderboardDetail.rank > Leaderboard.LEADERBOARD_VIEWABLE_RANK) {
                        return@loop
                    }
                    if (index >= Leaderboard.LEADERBOARD_VIEWABLE_RANK) {
                        return@loop
                    }
                    resultList.add(LeaderboardDetail(
                        rank = leaderboardDetail.rank,
                        playerId = leaderboardDetail.playerId.toString(),
                        handleName = leaderboardDetail.handleName.toString(),
                        score = leaderboardDetail.score.toInt(),
                    ))
                }
            }
            return resultList
        }
}
