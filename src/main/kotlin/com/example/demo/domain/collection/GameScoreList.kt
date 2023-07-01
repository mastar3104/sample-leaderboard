package com.example.demo.domain.collection

import com.example.demo.domain.entity.GameScore

data class GameScoreList(
    private val list: List<GameScore>
): List<GameScore> by list {

    val sortedByScoreDesc: GameScoreList
        get() {
            return GameScoreList(
                sortedByDescending{ it.score }
            )
        }

    val distinctByPlayerId: GameScoreList
        get() {
            return GameScoreList(
                distinctBy { it.playerId }
            )
        }
}
