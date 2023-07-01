package com.example.demo.domain.service

import com.example.demo.domain.collection.GameScoreList
import com.example.demo.domain.collection.PlayerList
import com.example.demo.domain.entity.Leaderboard
import com.example.demo.domain.model.LeaderboardDetail

object LeaderboardService {
    fun create(playerList: PlayerList, gameScoreList: GameScoreList): Leaderboard {
        val gameScoreListSortedByScoreDesc = gameScoreList.sortedByScoreDesc.distinctByPlayerId
        var rank = 1
        val leaderboardDetailList = gameScoreListSortedByScoreDesc.mapIndexedNotNull { index, gameScore ->
            val player = playerList.find(gameScore.playerId) ?: return@mapIndexedNotNull null
            gameScoreListSortedByScoreDesc.getOrNull(index-1)?.let { prevGameScore ->
                if (gameScore.score != prevGameScore.score) {
                    rank++
                }
            }
            LeaderboardDetail(
                rank = rank,
                playerId = player.playerId,
                handleName = player.handleName,
                score = gameScore.score,
            )
        }
        return Leaderboard(leaderboardDetailList)
    }
}