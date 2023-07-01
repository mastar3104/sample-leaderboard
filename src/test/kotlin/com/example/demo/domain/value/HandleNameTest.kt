package com.example.demo.domain.value

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class HandleNameTest {

    @ParameterizedTest
    @MethodSource("com.example.demo.domain.value.HandleNameTestKt#handleNameInitTestParam")
    fun `init_HandleNameのバリデーションチェック`(title: String, value: String, throwable: Boolean) {
        if (throwable) {
            assertThrows<IllegalArgumentException> {
                HandleName(value)
            }
        } else {
            HandleName(value)
        }
    }
}

fun handleNameInitTestParam() = listOf(
    arguments(
        "正常系(大文字、小文字、アンダースコア(_)、および数字の0-9, かつ20文字以内)",
        "Aa0_9zZ_Aa0_9zZ_Aa0_",
        false
    ),
    arguments(
        "平仮名が含まれる場合",
        "あああ",
        true
    ),
    arguments(
        "利用できない記号が含まれる場合",
        "Aa0_@9zZ_",
        true
    ),
    arguments(
        "空文字の場合",
        "",
        true
    ),
    arguments(
        "20文字を超過する場合",
        "Aa0_9zZ_Aa0_9zZ_Aa0_9zZ_Aa0_9zZ_Aa0_9zZ_Aa0_9zZ_",
        true
    ),
)