package com.example.mychats.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychats.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    /*
    TODO 1) добавить удаление сообщений (думаю через удерживание)
    TODO 2) если пришло уведомление, то сообщение должно быть последним именно от пользователя
    TODO 3) попробовать добавить время к сообщениям в чатах (рандомное)
     */
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatsAdapter: ChatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        scrollListener()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.chatList.observe(this) {
            chatsAdapter.submitList(it)
        }

        binding.pullToRefresh.setOnRefreshListener {

            binding.pullToRefresh.isRefreshing = false

            viewModel.addNewChat()

            binding.recyclerView.smoothScrollToPosition(0)

        }
        viewModel.getChats()


    }

    private fun setupRecyclerView() {
        chatsAdapter = ChatsAdapter()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = chatsAdapter
            recycledViewPool.setMaxRecycledViews(
                ChatsAdapter.VIEW_TYPE_ENABLE,
                ChatsAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ChatsAdapter.VIEW_TYPE_DISABLED,
                ChatsAdapter.MAX_POOL_SIZE
            )
            setupClickListener()
            setupSwipeListener()
        }
    }

    private fun setupSwipeListener() {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = chatsAdapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.deleteChat(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun setupClickListener() {
        chatsAdapter.onChatClickListener = {
            val intent = Intent(this, DialogActivity::class.java)
            intent.putExtra("user_name", it.name)
            intent.putExtra("user_photo", it.photo)
            intent.putExtra("id_user", it.id)
            startActivity(intent)
            Log.i("intent_info_about_user", it.toString())
        }
    }

    private fun scrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!binding.recyclerView.canScrollVertically(SCROLL_DOWN) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.i("MyLog", "Дошли до конца")
                    viewModel.addSomeNewChats()
                }
            }
        })
    }

    companion object {
        const val SCROLL_DOWN = 1
    }
}
