package com.example.mychats.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mychats.R
import com.example.mychats.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialogBinding

    private lateinit var dialogAdapter: DialogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolBar)
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.i("MyLogs", mode.toString())
        binding.editMessageText.text = mode.toString()
        binding.myToolBar.title = mode.toString()
        //TODO передавать данные
    }

    companion object{
        const val EXTRA_SCREEN_MODE = "chat"
        const val MESSAGE = "HELLO"
    }
}