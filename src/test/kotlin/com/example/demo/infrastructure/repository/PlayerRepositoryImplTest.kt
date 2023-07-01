package com.example.demo.infrastructure.repository

import com.example.demo.domain.collection.PlayerList
import com.example.demo.domain.entity.Player
import com.example.demo.domain.value.HandleName
import com.example.demo.domain.value.PlayerId
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PlayerRepositoryImplTest {
    @Autowired
    lateinit var playerRepository: PlayerRepositoryImpl

    @Test
    fun `get_csvのマッピングができること`() {
        runBlocking {
            val actual = playerRepository.get()
            assertEquals(
                PlayerList(
                    listOf(
                        Player(
                            playerId = PlayerId("nrogh923u50sj"),
                            handleName = HandleName("player1")
                        ),
                        Player(
                            playerId = PlayerId("amort8u3450t"),
                            handleName = HandleName("player2")
                        ),
                        Player(
                            playerId = PlayerId("fmwqor49u5af"),
                            handleName = HandleName("player3")
                        ),
                        Player(
                            playerId = PlayerId("asgqr_grg09w"),
                            handleName = HandleName("player4")
                        ),
                        Player(
                            playerId = PlayerId("ptqs_r90tegs"),
                            handleName = HandleName("player5")
                        ),
                        Player(
                            playerId = PlayerId("sfpe695ejw_q"),
                            handleName = HandleName("player6")
                        ),
                        Player(
                            playerId = PlayerId("134534t09ud0"),
                            handleName = HandleName("player7")
                        ),
                        Player(
                            playerId = PlayerId("vmer0rt9ejrd"),
                            handleName = HandleName("player8")
                        ),
                        Player(
                            playerId = PlayerId("gno4328rsd_f"),
                            handleName = HandleName("player9")
                        ),
                        Player(
                            playerId = PlayerId("sfew__svdsfe"),
                            handleName = HandleName("player10")
                        ),
                        Player(
                            playerId = PlayerId("gm4oy9jfqems"),
                            handleName = HandleName("player11")
                        ),
                        Player(
                            playerId = PlayerId("MSEOgwMP_0t"),
                            handleName = HandleName("player12")
                        ),
                    )
                ), actual
            )
        }
    }
}