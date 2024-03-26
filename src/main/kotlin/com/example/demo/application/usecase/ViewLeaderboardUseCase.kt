package com.example.demo.application.usecase

import com.example.demo.application.model.LeaderBoardModel
import com.example.demo.domain.repository.GameScoreRepository
import com.example.demo.domain.repository.PlayerRepository
import com.example.demo.domain.service.LeaderboardService
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Component

@Component
class ViewLeaderboardUseCase(
    val gameScoreRepository: GameScoreRepository,
    val playerRepository: PlayerRepository,
) {
    val objectMapper = jacksonObjectMapper()

    suspend fun view() = coroutineScope {

        val gameScoreListCoroutine = async {
            gameScoreRepository.get()
        }
        val playerListCoroutine = async {
            playerRepository.get()
        }

        val leaderboard = LeaderboardService.create(
            playerList = playerListCoroutine.await(),
            gameScoreList = gameScoreListCoroutine.await(),
        )

        val viewModel = LeaderBoardModel(leaderboard)
        objectMapper.writeValueAsString(viewModel.viewList)
    }
}