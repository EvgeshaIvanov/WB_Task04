package com.example.mychats.domain

data class ChatData(
    val name: String,
    val message: String,
    val photo: String,
    val read: Boolean
)
