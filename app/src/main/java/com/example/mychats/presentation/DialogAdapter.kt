package com.example.mychats.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mychats.databinding.ItemListBinding
import com.example.mychats.databinding.ItemListMessageBinding
import com.example.mychats.domain.ChatData

class DialogAdapter: RecyclerView.Adapter<DialogAdapter.DialogHolder>() {


    var list = listOf<ChatData>()
    @SuppressLint("NotifyDataSetChanged")
    set(value){
        field = value
        notifyDataSetChanged()
    }

    class DialogHolder(binding: ItemListMessageBinding): RecyclerView.ViewHolder(binding.root){
        val blockUserMessage: CardView = binding.viewUserMessage
        val chatUserMessage: TextView  = binding.editMessageText
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListMessageBinding.inflate(inflater, parent, false)

        return DialogHolder(binding)
    }

    override fun onBindViewHolder(holder: DialogHolder, position: Int) {
        if (list[position].id == position){
            holder.chatUserMessage.text = list[position].message
        }
    }

    override fun getItemCount(): Int = list.size
}