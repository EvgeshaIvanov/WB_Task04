package com.example.mychats.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychats.data.ChatsRepositoryImpl
import com.example.mychats.domain.GetMessagesUseCase
import com.example.mychats.domain.ChatData

class MessagesViewModel(): ViewModel() {

    private val repository = ChatsRepositoryImpl

    private val getMessagesUseCase = GetMessagesUseCase(repository)

    val messagesList = MutableLiveData<List<ChatData>>()

    fun getMessages() {
        val list = getMessagesUseCase.getMessages()
        messagesList.value = list
    }
}