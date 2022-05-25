package com.example.mychats.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mychats.R
import com.example.mychats.databinding.ItemListMessageBinding
import com.example.mychats.domain.ChatData

class DialogAdapter : RecyclerView.Adapter<DialogViewHolder>() {

    var onLongClickListener: ((ChatData) -> Unit)? = null

    var list = listOf<ChatData>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            Log.i("ListInfo", list.toString())
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListMessageBinding.inflate(inflater, parent, false)

        return DialogViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: DialogViewHolder, position: Int) {
        val message = list[position]
        if (list[position].userMessage) {
            with(viewHolder) {
                blockYourMessage.visibility = View.GONE
                chatUserMessage.text = message.text
                timeUserMessage.text = getRandomTime()
            }
        } else {
            with(viewHolder) {
                blockUserMessage.visibility = View.GONE
                chatYourMessage.text = message.text
                timeYourMessage.text = getRandomTime()
            }
        }

        blockYourMessageClickListener(viewHolder, message)

        blockUserMessageClickListener(viewHolder, message)
    }

    private fun blockUserMessageClickListener(
        viewHolder: DialogViewHolder,
        message: ChatData
    ) {
        viewHolder.blockUserMessage.setOnLongClickListener {
            onLongClickListener?.invoke(message)
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    viewHolder.itemView.context,
                    R.anim.animtation_click
                )
            )
            val popup = PopupMenu(viewHolder.itemView.context, it)
            popup.inflate(R.menu.options_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.delete_item -> {
                        Toast.makeText(viewHolder.itemView.context, "Soon", Toast.LENGTH_SHORT)
                            .show()
                        true
                    }
                    R.id.change_text_item -> {
                        Toast.makeText(viewHolder.itemView.context, "Soon", Toast.LENGTH_SHORT)
                            .show()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
            true
        }
    }

    private fun blockYourMessageClickListener(
        viewHolder: DialogViewHolder,
        message: ChatData
    ) {
        viewHolder.blockYourMessage.setOnLongClickListener {
            onLongClickListener?.invoke(message)
            it.startAnimation(
                AnimationUtils.loadAnimation(
                    viewHolder.itemView.context,
                    R.anim.animtation_click
                )
            )
            val popup = PopupMenu(viewHolder.itemView.context, it)
            popup.inflate(R.menu.options_menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.delete_item -> {
                        Toast.makeText(viewHolder.itemView.context, "Soon", Toast.LENGTH_SHORT)
                            .show()
                        true
                    }
                    R.id.change_text_item -> {
                        Toast.makeText(viewHolder.itemView.context, "Soon", Toast.LENGTH_SHORT)
                            .show()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = list[position]
        return if (item.userMessage) {
            VIEW_USER_MESSAGE
        } else {
            VIEW_YOUR_MESSAGE
        }
    }

    override fun getItemCount(): Int = list.size

    private fun getRandomTime(): String {
        val hours = (10..23).random()
        val minutes = (10..59).random()
        return "$hours:$minutes"
    }

    companion object {
        const val VIEW_YOUR_MESSAGE = 0
        const val VIEW_USER_MESSAGE = 1
        const val MAX_POOL_SIZE = 30
    }
}