package com.example.demo.domain.model

import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score

data class LeaderboardDetail(
    val rank: Int,
    val playerId: PlayerId,
    val handleName: HandleName,
    val score: Score,
)
