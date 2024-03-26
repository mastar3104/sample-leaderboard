package com.example.demo.domain.value

data class HandleName(
    private val value: String
) {
    init {
        require(value.matches(HANDLE_NAME_RULE)) {
            "HandleNameに利用できない文字列が含まれています"
        }
        require(value.length <= HANDLE_NAME_MAX_LENGTH) {
            "HandleNameが${HANDLE_NAME_MAX_LENGTH}文字を超過しています。"
        }
    }

    companion object {
        val HANDLE_NAME_RULE = Regex("(_|[a-z]|[A-Z]|[0-9])+")
        const val HANDLE_NAME_MAX_LENGTH = 20
    }

    override fun toString(): String {
        return value
    }
}
