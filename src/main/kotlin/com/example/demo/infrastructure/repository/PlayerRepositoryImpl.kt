package com.example.demo.infrastructure.repository

import com.example.demo.domain.collection.PlayerList
import com.example.demo.domain.entity.Player
import com.example.demo.domain.repository.PlayerRepository
import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId
import com.example.demo.infrastructure.resource.GameEntryResource
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Repository

@Repository
class PlayerRepositoryImpl(
    @Value("\${file.player.path:}") private val filePath: String,
    private val resourceLoader: ResourceLoader
): PlayerRepository {
    private final val csvMapper = CsvMapper().apply {
        registerModule(KotlinModule.Builder().build())
    }
    private val csvSchema = CsvSchema.builder()
        .addColumn("player_id")
        .addColumn("handle_name")
        .setUseHeader(true)
        .build()

    private val playerList by lazy {
        val csvString = resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + filePath).url

        val objectMappingIterator = csvMapper.readerFor(GameEntryResource::class.java)
            .with(csvSchema)
            .readValues<GameEntryResource>(csvString)

        val playerList = mutableListOf<Player>()
        objectMappingIterator.forEach { resource ->
            playerList.add(
                Player(
                    playerId = PlayerId(resource.playerId),
                    handleName = HandleName(resource.handleName),
                )
            )
        }
        PlayerList(playerList)
    }
    override suspend fun get(): PlayerList {
        return playerList
    }
}