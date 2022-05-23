package com.example.mychats.domain

data class ChatData(
    var id: Int = UNDEFINED_ID,
    val name: String = "",
    val message: String = "",
    val photo: String = "",
    val readState: Boolean = false,
    var text: String = "",
    var time: String = "",
    var userMessage: Boolean = false
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}