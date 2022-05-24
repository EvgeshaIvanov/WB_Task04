package com.example.mychats.presentation

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mychats.databinding.ItemListMessageBinding

class DialogViewHolder(binding: ItemListMessageBinding) : RecyclerView.ViewHolder(binding.root) {
    val blockUserMessage: CardView = binding.viewUserMessage
    val chatUserMessage: TextView = binding.userMessageText
    val blockYourMessage: CardView = binding.viewYourMessage
    val chatYourMessage: TextView = binding.yourMessageText
}