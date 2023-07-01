package com.example.demo.domain.repository

import com.example.demo.domain.collection.GameScoreList

interface GameScoreRepository {
    suspend fun get(): GameScoreList
}