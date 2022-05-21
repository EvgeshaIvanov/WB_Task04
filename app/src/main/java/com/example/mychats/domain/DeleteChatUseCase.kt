package com.example.mychats.domain

class DeleteChatUseCase(private val repository: ChatsRepository) {

    fun deleteChat(chatData: ChatData){
        repository.deleteChat(chatData)
    }
}