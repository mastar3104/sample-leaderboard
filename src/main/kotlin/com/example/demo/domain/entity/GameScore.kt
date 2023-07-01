package com.example.demo.domain.entity

import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
import java.time.Instant

data class GameScore(
    val playerId: PlayerId,
    val score: Score,
    val createTimestamp: Instant,
)
