package com.example.mychats.domain

import androidx.lifecycle.MutableLiveData

interface ChatsRepository {

    fun getChats(): List<ChatData>

    fun addNewChat(): List<ChatData>

    fun addSomeNewChats(): List<ChatData>

    fun deleteChat(chatData: ChatData)

}