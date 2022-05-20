package com.example.mychats.domain

import androidx.lifecycle.MutableLiveData

interface ChatsRepository {

    fun getChats(): List<ChatData>

    fun clearChats(list: MutableLiveData<List<ChatData>>)

    fun addNewChat(chatData: ChatData)

}