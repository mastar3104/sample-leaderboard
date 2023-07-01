package com.example.demo.domain.entity

import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId

data class Player(
    val playerId: PlayerId,
    val handleName: HandleName,
)
