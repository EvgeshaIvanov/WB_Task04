package com.example.mychats.domain

class GetChatsUseCase(private val chatsRepository: ChatsRepository) {

    fun getChats(): List<ChatData> {
        return chatsRepository.getChats()
    }

}