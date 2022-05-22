package com.example.mychats.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychats.R
import com.example.mychats.databinding.ActivityMainBinding
import com.example.mychats.presentation.DialogActivity.Companion.EXTRA_SCREEN_MODE
import com.example.mychats.presentation.DialogActivity.Companion.MESSAGE


class MainActivity : AppCompatActivity() {

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

            Log.i("Test", it.toString())
        }

        binding.pullToRefresh.setOnRefreshListener {
            binding.pullToRefresh.isRefreshing = false

            viewModel.addNewChat()

            binding.recyclerView.smoothScrollToPosition(0)
            //todo возвращать в начало лист

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
            intent.putExtra(EXTRA_SCREEN_MODE, "name - ${it.id}, ${it.name}, ${it.message}")
            startActivity(intent)
            Log.i("Main", it.toString())
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
