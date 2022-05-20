package com.example.mychats.presentation

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mychats.R


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ChatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
        val refresh = findViewById<SwipeRefreshLayout>(R.id.pull_to_refresh)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.chatList.observe(this) {
            adapter.chats = it
            Log.i("MYTEST", it.toString())
        }
        viewModel.getChats()

        refresh.setOnRefreshListener {
            viewModel.getClearChats()

            refresh.isRefreshing = false
        }

    }

    private fun setupRecyclerView() {
        val rvChats = findViewById<RecyclerView>(R.id.recycler_view)
        adapter = ChatsAdapter()
        rvChats.layoutManager = LinearLayoutManager(this)
        rvChats.adapter = adapter
        rvChats.recycledViewPool.setMaxRecycledViews(
            ChatsAdapter.VIEW_TYPE_ENABLE,
            ChatsAdapter.MAX_POOL_SIZE
        )
        rvChats.recycledViewPool.setMaxRecycledViews(
            ChatsAdapter.VIEW_TYPE_DISABLED,
            ChatsAdapter.MAX_POOL_SIZE
        )
    }
}
