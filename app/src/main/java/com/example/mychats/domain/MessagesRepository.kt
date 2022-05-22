package com.example.mychats.domain

interface MessagesRepository {

    fun getMessages(): List<ChatData>

}