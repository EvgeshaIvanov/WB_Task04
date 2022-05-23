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

    private val getMessagesUseCase = GetMessagesUseCase(repository)

    private val sendMessageUseCase = SendMessageUseCase(repository)

    private val addSomeMessagesUseCase = AddSomeMessagesUseCase(repository)


    val chatList = MutableLiveData<List<ChatData>>()

    val dialogsList = MutableLiveData<List<ChatData>>()

    fun getChats() {
        val list = getChatsUseCase.getChats()
        chatList.value = list
    }

    fun addNewChat() {
        val list = addNewChatUseCase.addNewChat()
        chatList.value = list
        getChats()
    }

    fun addSomeNewChats() {
        val list = addNewChatsUseCase.addSomeNewChats()
        chatList.value = list
        getChats()
    }

    fun deleteChat(chatData: ChatData) {
        deleteChatUseCase.deleteChat(chatData)
        getChats()
    }

    fun getMessages() {
        val list = getMessagesUseCase.getMessages()
        dialogsList.value = list

    }

    fun sendMessage(text: String) {
        val list = sendMessageUseCase.sendMessage(text)
        dialogsList.value = list
    }

    fun addSomeMessages() {
        val list = addSomeMessagesUseCase.addSomeMessages()
        dialogsList.value = list
    }


}