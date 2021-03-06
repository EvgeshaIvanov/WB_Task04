package com.example.mychats.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mychats.R
import com.example.mychats.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityDialogBinding

    private lateinit var dialogAdapter: DialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = getColor(R.color.telegramm_user_message)
        }
        setSupportActionBar(binding.myToolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val name = intent.getStringExtra("user_name")
        val photo = intent.getStringExtra("user_photo")
        setUserImage(photo)
        binding.toolbarTittle.text = name.toString()
        setupRecyclerView()
        scrollListener()
        binding.recyclerViewDialogs.smoothScrollToPosition(dialogAdapter.itemCount)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.dialogsList.observe(this) {
            dialogAdapter.list = it
            Log.i("dialogAdapter", it.toString())
        }
        viewModel.getMessages()
        binding.sendMessageButton.setOnClickListener {
            if (binding.editMessageText.text.isNotEmpty()) {
                viewModel.sendMessage(binding.editMessageText.text.toString())
                binding.editMessageText.text = null
            }

            binding.recyclerViewDialogs.smoothScrollToPosition(dialogAdapter.itemCount)
        }
        binding.arrowBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }



    private fun setUserImage(photo: String?) {
        if (photo != null) {
            Glide.with(binding.avatarImage.context)
                .load(photo)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_background)
                .into(binding.avatarImage)

        }
    }


    private fun setupRecyclerView() {
        dialogAdapter = DialogAdapter()

        binding.recyclerViewDialogs.apply {
            layoutManager = LinearLayoutManager(this@DialogActivity)

            adapter = dialogAdapter
            recycledViewPool.setMaxRecycledViews(
                DialogAdapter.VIEW_YOUR_MESSAGE,
                DialogAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                DialogAdapter.VIEW_USER_MESSAGE,
                DialogAdapter.MAX_POOL_SIZE
            )
        }

    }




    private fun scrollListener() {
        binding.recyclerViewDialogs.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!binding.recyclerViewDialogs.canScrollVertically(SCROLL_UP)
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                ) {
                    Log.i("MyLog", "?????????? ???? ????????????")
                    viewModel.addSomeMessages()
                }
            }
        })
    }

    companion object {
        const val SCROLL_UP = -1
    }
}