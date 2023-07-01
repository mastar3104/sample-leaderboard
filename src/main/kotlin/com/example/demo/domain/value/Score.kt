package com.example.demo.domain.value

data class Score(
    private val value: Int
): Comparable<Score> {
    init {
        require(value >= 0) {
            "Scoreが負の値となっています。"
        }
    }

    override fun compareTo(other: Score): Int {
        return value.compareTo(other.value)
    }
}
