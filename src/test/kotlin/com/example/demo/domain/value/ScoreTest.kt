package com.example.demo.domain.value

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class ScoreTest {

    @ParameterizedTest
    @MethodSource("com.example.demo.domain.value.ScoreTestKt#scoreInitTestParam")
    fun `init_Scoreのバリデーションチェック`(title: String, value: Int, throwable: Boolean) {
        if (throwable) {
            assertThrows<IllegalArgumentException> {
                Score(value)
            }
        } else {
            Score(value)
        }
    }
}

fun scoreInitTestParam() = listOf(
    arguments(
        "正常系",
        100,
        false
    ),
    arguments(
        "0の場合",
        100,
        false
    ),
    arguments(
        "負の値の場合",
        -1,
        true
    ),
)