package com.example.demo.domain.value

data class PlayerId(
    private val value: String
): Comparable<PlayerId> {

    init {
        require(value.matches(PLAYER_ID_RULE)) {
            "PlayerIdに利用できない文字列が含まれています"
        }
        require(value.length <= PLAYER_ID_MAX_LENGTH) {
            "PlayerIdが${PLAYER_ID_MAX_LENGTH}文字を超過しています。"
        }
    }

    companion object {
        val PLAYER_ID_RULE = Regex("(_|[a-z]|[A-Z]|[0-9])+")
        const val PLAYER_ID_MAX_LENGTH = 20
    }

    override fun compareTo(other: PlayerId): Int {
        return value.compareTo(other.value)
    }

    override fun toString(): String {
        return value
    }
}
