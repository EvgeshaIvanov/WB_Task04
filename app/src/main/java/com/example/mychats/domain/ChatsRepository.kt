package com.example.mychats.domain

interface ChatsRepository {

    fun getChats(): List<ChatData>

    fun addNewChat(): List<ChatData>

    fun addSomeNewChats(): List<ChatData>

    fun deleteChat(chatData: ChatData)

    fun getMessages(): List<ChatData>

    fun sendMessage(text: String): List<ChatData>

    fun addSomeMessages(): List<ChatData>

    fun deleteMessage(message: String)

}