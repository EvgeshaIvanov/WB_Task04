package com.example.mychats.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.mychats.domain.ChatData

class ChatsItemDiffCallback : DiffUtil.ItemCallback<ChatData>() {
    override fun areItemsTheSame(oldItem: ChatData, newItem: ChatData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChatData, newItem: ChatData): Boolean {
        return oldItem == newItem
    }
}