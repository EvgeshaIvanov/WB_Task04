package com.example.mychats.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychats.data.ChatsRepositoryImpl
import com.example.mychats.domain.AddNewChatUseCase
import com.example.mychats.domain.ChatData
import com.example.mychats.domain.GetChatsUseCase
import com.example.mychats.domain.GetClearChatsUseCase

class MainViewModel : ViewModel() {

    private val repository = ChatsRepositoryImpl

    private val getChatsUseCase = GetChatsUseCase(repository)
    private val getClearChatsUseCase = GetClearChatsUseCase(repository)
    private val addNewChatUseCase = AddNewChatUseCase(repository)


     val chatList = MutableLiveData<List<ChatData>>()

    fun getChats() {
        val list = getChatsUseCase.getChats()
        chatList.value = list
    }

    fun getClearChats(){
        getClearChatsUseCase.clearChats(chatList)
    }

    fun addNewChatUseCase(){
        TODO()
    }

}