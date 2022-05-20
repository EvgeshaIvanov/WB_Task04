package com.example.mychats.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mychats.R
import com.example.mychats.databinding.ItemListBinding
import com.example.mychats.databinding.ItemListNewMessageBinding
import com.example.mychats.domain.ChatData
import java.lang.RuntimeException

class ChatsAdapter : RecyclerView.Adapter<ChatsAdapter.ChatViewHolder>() {

    var chats = listOf<ChatData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_DISABLED -> R.layout.item_list
            VIEW_TYPE_ENABLE -> R.layout.item_list_new_message
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = chats[position]
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
            if (chat.read) {
                newMessageNotification.visibility = View.VISIBLE
            }
            chatView.setOnClickListener {
                newMessageNotification.visibility = View.GONE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = chats[position]

        return if (item.read) {
            VIEW_TYPE_ENABLE
        } else {
            VIEW_TYPE_DISABLED
        }

    }

    override fun getItemCount(): Int {
        return chats.size
    }

    class ChatViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        const val VIEW_TYPE_ENABLE = 100
        const val VIEW_TYPE_DISABLED = 101

        const val MAX_POOL_SIZE = 15
    }
}