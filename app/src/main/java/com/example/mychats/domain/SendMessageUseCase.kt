package com.example.mychats.domain

class SendMessageUseCase(private val repository: ChatsRepository) {

    fun sendMessage(text: String): List<ChatData> {
        return repository.sendMessage(text)
    }
}