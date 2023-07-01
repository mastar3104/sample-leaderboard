package com.example.demo.domain.collection

import com.example.demo.domain.entity.Player
import com.example.demo.domain.value.PlayerId

data class PlayerList(
    private val list: List<Player>
): List<Player> by list {

    fun find(playerId: PlayerId): Player? {
        return firstOrNull { it.playerId == playerId }
    }
}