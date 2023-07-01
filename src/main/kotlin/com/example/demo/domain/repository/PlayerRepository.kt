package com.example.demo.domain.repository

import com.example.demo.domain.collection.PlayerList

interface PlayerRepository {
    suspend fun get(): PlayerList
}