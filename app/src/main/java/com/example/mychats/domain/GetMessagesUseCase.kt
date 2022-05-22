package com.example.mychats.domain

class GetMessagesUseCase(private val messagesRepository: MessagesRepository) {

    fun getMessages(): List<ChatData>{
        return messagesRepository.getMessages()
    }

}