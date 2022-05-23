package com.example.mychats.domain

class AddSomeMessagesUseCase(private val repository: ChatsRepository) {

    fun addSomeMessages(): List<ChatData> {
        return repository.addSomeMessages()
    }

}