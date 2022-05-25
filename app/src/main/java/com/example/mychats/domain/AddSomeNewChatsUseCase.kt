package com.example.mychats.domain

class AddSomeNewChatsUseCase(private val repository: ChatsRepository) {

    fun addSomeNewChats(): List<ChatData> {
        return repository.addSomeNewChats()
    }

}