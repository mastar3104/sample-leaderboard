package com.example.demo.infrastructure.repository

import com.example.demo.domain.collection.GameScoreList
import com.example.demo.domain.entity.GameScore
import com.example.demo.domain.repository.GameScoreRepository
import com.example.demo.domain.value.PlayerId
import com.example.demo.domain.value.Score
import com.example.demo.infrastructure.resource.GameScoreResource
import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Repository
import com.fasterxml.jackson.module.kotlin.KotlinModule

@Repository
class GameScoreRepositoryImpl(
    @Value("\${file.score.path:}") private val filePath: String,
    private val resourceLoader: ResourceLoader
) : GameScoreRepository {
    private final val csvMapper = CsvMapper().apply {
        registerModule(KotlinModule.Builder().build())
    }

    private val csvSchema: CsvSchema = csvMapper
        .schemaFor(GameScoreResource::class.java)
        .withHeader()

    override suspend fun get(): GameScoreList {
        val csvString = resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + filePath).url
        val objectMappingIterator = csvMapper.readerFor(GameScoreResource::class.java)
            .with(csvSchema)
            .readValues<GameScoreResource>(csvString)

        val gameScoreList = mutableListOf<GameScore>()
        objectMappingIterator.forEach { resource ->
            gameScoreList.add(
                GameScore(
                    playerId = PlayerId(resource.playerId),
                    score = Score(resource.score),
                    createTimestamp = resource.createTimestampToInstant(),
                )
            )
        }
        return GameScoreList(gameScoreList)
    }
}