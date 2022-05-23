package com.example.mychats.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.mychats.R
import com.example.mychats.databinding.ItemListBinding
import com.example.mychats.domain.ChatData

class ChatsAdapter : ListAdapter<ChatData, ChatViewHolder>(ChatsItemDiffCallback()) {

    var onChatClickListener: ((ChatData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = getItem(position)

        bind(holder, chat)
    }

    private fun bind(
        holder: ChatViewHolder,
        chat: ChatData
    ) {
        with(holder.binding) {
            nameView.text = chat.name
            messageView.text = chat.message
            if (chat.photo.isNotBlank()) {
                Glide.with(avatarImage.context)
                    .load(chat.photo)
                    .circleCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(avatarImage)
            } else {
                Glide.with(avatarImage.context).clear(avatarImage)
                avatarImage.setImageResource(R.drawable.ic_launcher_foreground)
            }
            if (!chat.readState) {
                nameView.typeface.isItalic
                newMessageNotification.visibility = View.VISIBLE
            }
            chatView.setOnClickListener {
                chat.readState = true
                newMessageNotification.visibility = View.GONE
                onChatClickListener?.invoke(chat)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)

        return if (item.readState) {
            VIEW_TYPE_ENABLE
        } else {
            VIEW_TYPE_DISABLED
        }
    }


    companion object {
        const val VIEW_TYPE_ENABLE = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 15
    }
}