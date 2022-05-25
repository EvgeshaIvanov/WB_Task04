package com.example.mychats.domain

class GetMessagesUseCase(private val messagesRepository: ChatsRepository) {

    fun getMessages(): List<ChatData> {
        return messagesRepository.getMessages()
    }

}