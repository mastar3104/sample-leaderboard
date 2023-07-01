package com.example.demo.infrastructure.resource

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class GameScoreResource(
    @JsonProperty("create_timestamp")
    val createTimestamp: String,
    @JsonProperty("player_id")
    val playerId: String,
    val score: Int,
) {
    companion object {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")!!
    }

    fun createTimestampToInstant(): Instant {
        return Instant.from(
            LocalDateTime.parse(createTimestamp, formatter).atZone(ZoneId.systemDefault())
        )
    }
}
