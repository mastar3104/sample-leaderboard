package com.example.demo.infrastructure.resource

import com.fasterxml.jackson.annotation.JsonProperty

data class GameEntryResource(
    @JsonProperty("player_id")
    val playerId: String,
    @JsonProperty("handle_name")
    val handleName: String,
)
