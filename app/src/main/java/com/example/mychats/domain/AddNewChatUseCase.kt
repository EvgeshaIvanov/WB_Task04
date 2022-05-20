package com.example.mychats.domain

class AddNewChatUseCase(private val repository: ChatsRepository) {


    fun addNewChat(chatData: ChatData){
        repository.addNewChat(chatData)
    }

}