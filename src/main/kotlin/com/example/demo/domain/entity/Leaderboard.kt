package com.example.demo.domain.entity

import com.example.demo.domain.model.LeaderboardDetail

data class Leaderboard(
//    val leaderboardId: LeaderboardId,
    private val _list: List<LeaderboardDetail>
) {
    val viewList: List<LeaderboardDetail> = _list.sortedBy { it.playerId }.sortedBy { it.rank }.filterIndexed { index, leaderboardDetail ->
        _list.getOrNull(index-1)?.let { prevLeaderboardDetail ->
            if (leaderboardDetail.rank == prevLeaderboardDetail.rank) {
                return@filterIndexed true
            }
        }
        if (leaderboardDetail.rank > LEADERBOARD_VIEWABLE_RANK) {
            return@filterIndexed false
        }
        if (index > LEADERBOARD_VIEWABLE_RANK) {
            return@filterIndexed false
        }
        true
    }

    init {
        require(!(_list.groupBy { it.playerId }.any { it.value.size > 1 })) {
            "playerIdが重複したLeaderboardが生成されています"
        }
    }

    companion object {
        const val LEADERBOARD_VIEWABLE_RANK = 10
    }
}
