package com.example.mychats.domain

data class ChatData(
    var id: Int = UNDEFINED_ID,
    val name: String = "",
    val message: String = "",
    val photo: String = "",
    var readState: Boolean = true,
    var text: String? = null,
    var time: String = "",
    var userMessage: Boolean = false
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}