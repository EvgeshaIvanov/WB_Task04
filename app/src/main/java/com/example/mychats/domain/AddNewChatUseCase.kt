package com.example.mychats.domain

class AddNewChatUseCase(private val repository: ChatsRepository) {


    fun addNewChat(): List<ChatData> {
        return repository.addNewChat()
    }


}