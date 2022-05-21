package com.example.mychats.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mychats.data.ChatsRepositoryImpl
import com.example.mychats.domain.*

class MainViewModel : ViewModel() {

    private val repository = ChatsRepositoryImpl

    private val getChatsUseCase = GetChatsUseCase(repository)

    private val addNewChatUseCase = AddNewChatUseCase(repository)

    private val addNewChatsUseCase = AddSomeNewChatsUseCase(repository)

    private val deleteChatUseCase = DeleteChatUseCase(repository)

     val chatList = MutableLiveData<List<ChatData>>()

    fun getChats() {
        val list = getChatsUseCase.getChats()
        chatList.value = list
    }

    fun addNewChat(){
        val list = addNewChatUseCase.addNewChat()
        chatList.value = list
    }

    fun addSomeNewChats(){
        val list = addNewChatsUseCase.addSomeNewChats()
        chatList.value = list
    }

    fun deleteChat(chatData: ChatData){
        deleteChatUseCase.deleteChat(chatData)
        getChats()
    }

}