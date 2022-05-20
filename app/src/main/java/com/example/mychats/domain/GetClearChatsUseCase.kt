package com.example.mychats.domain

import androidx.lifecycle.MutableLiveData

class GetClearChatsUseCase(private val chatsRepository: ChatsRepository) {

    fun clearChats(list: MutableLiveData<List<ChatData>>) {
        chatsRepository.clearChats(list)
    }

}