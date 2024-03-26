package com.example.demo.domain.entity

import com.example.demo.domain.model.LeaderboardDetail

data class Leaderboard(
//    val leaderboardId: LeaderboardId,
    val detailList: List<LeaderboardDetail>
) {
    init {
        require(!(detailList.groupBy { it.playerId }.any { it.value.size > 1 })) {
            "playerIdが重複したLeaderboardが生成されています"
        }
    }

    companion object {
        const val LEADERBOARD_VIEWABLE_RANK = 10
    }
}
