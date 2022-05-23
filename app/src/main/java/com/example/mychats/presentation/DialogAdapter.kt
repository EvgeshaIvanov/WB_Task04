package com.example.mychats.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mychats.databinding.ItemListMessageBinding
import com.example.mychats.domain.ChatData

class DialogAdapter : RecyclerView.Adapter<DialogAdapter.DialogHolder>() {


    var list = listOf<ChatData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            Log.i("ListInfo", list.toString())
            field = value
            notifyDataSetChanged()
        }

    class DialogHolder(binding: ItemListMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        val blockUserMessage: CardView = binding.viewUserMessage
        val chatUserMessage: TextView = binding.userMessageText
        val blockYourMessage: CardView = binding.viewYourMessage
        val chatYourMessage: TextView = binding.yourMessageText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListMessageBinding.inflate(inflater, parent, false)

        return DialogHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogHolder, position: Int) {
        val message = list[position]

        if (list[position].userMessage) {
            with(holder) {
                blockYourMessage.visibility = View.GONE
                chatUserMessage.text = message.text
            }
        }
        else {
            with(holder){

                blockUserMessage.visibility = View.GONE
                chatYourMessage.text = message.text
            }
        }
        Log.i("positon", message.toString())

    }

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return if (item.userMessage){
            VIEW_USER_MESSAGE
        } else {
            VIEW_YOUR_MESSAGE
        }

    }

    override fun getItemCount(): Int = list.size
    companion object{
        const val VIEW_YOUR_MESSAGE = 0
        const val VIEW_USER_MESSAGE = 1
        const val MAX_POOL_SIZE = 30
    }
}