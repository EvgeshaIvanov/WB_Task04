package com.example.mychats.domain

class DeleteMessageUseCase(private val repository: ChatsRepository) {

    fun deleteMessage(message: String) {
        repository.deleteMessage(message)
    }
}