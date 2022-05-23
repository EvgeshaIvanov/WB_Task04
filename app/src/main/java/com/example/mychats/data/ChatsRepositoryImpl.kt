package com.example.mychats.data

import android.os.Build
import android.util.Log
import com.example.mychats.domain.ChatData
import com.example.mychats.domain.ChatsRepository
import com.github.javafaker.Faker
import java.time.LocalTime
import kotlin.random.Random

object ChatsRepositoryImpl : ChatsRepository {

    private val faker: Faker = Faker.instance()


    private val IMAGES = mutableListOf(
        "https://images.unsplash.com/photo-1600267185393-e158a98703de?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NjQ0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/photo-1579710039144-85d6bdffddc9?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0Njk1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0ODE0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/photo-1620252655460-080dbec533ca?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzQ1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/photo-1613679074971-91fc27180061?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzUz&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/photo-1485795959911-ea5ebf41b6ae?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzU4&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/photo-1545996124-0501ebae84d0?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0NzY1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/flagged/photo-1568225061049-70fb3006b5be?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0Nzcy&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/photo-1567186937675-a5131c8a89ea?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0ODYx&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
        "https://images.unsplash.com/photo-1546456073-92b9f0a8d413?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0ODY1&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800"
    )

    private var chats = mutableListOf<ChatData>()

    private var dialogs = mutableListOf<ChatData>()

    private var autoIncrementId = 0

    init {
        IMAGES.shuffle()
        chats = (0..10).map {
            ChatData(
                name = faker.name().firstName(),
                message = faker.company().profession(),
                photo = IMAGES[it % IMAGES.size],
                id = autoIncrementId++
            )
        }.toMutableList()
    }

    override fun getChats(): List<ChatData> {
        return chats.toList()
    }

    override fun addNewChat(): List<ChatData> {
        val chat = ChatData(
            name = faker.name().firstName(),
            message = faker.company().profession(),
            photo = IMAGES[(0..9).random()],
            readState = false,
            id = autoIncrementId++,
            userMessage = true
        )
        chats.add(0, chat)
        return chats
    }

    override fun addSomeNewChats(): List<ChatData> {
        for (el in 0..10) {
            val chat = ChatData(
                name = faker.name().firstName(),
                message = faker.company().profession(),
                photo = IMAGES[(0..9).random()],
                id = autoIncrementId++
            )
            chats.add(chat)
        }
        getChats()
        return chats
    }

    override fun deleteChat(chatData: ChatData) {
        autoIncrementId--
        chats.remove(chatData)
    }

    override fun getMessages(): List<ChatData> {
        dialogs = (1..10).map {
            ChatData(
                text = textMessagesList[(0..9).random()],
                userMessage = Random.nextBoolean(),
                id = autoIncrementId
            )
        }.toMutableList()
        return dialogs.toList()
    }

    override fun sendMessage(text: String): List<ChatData> {
        val message = ChatData(
            text = text,
            userMessage = false
        )
        dialogs.add(message)
        return dialogs
    }

    override fun addSomeMessages(): List<ChatData> {
        for (el in 0..9) {
            val message = ChatData(
                text = textMessagesList[(10..20).random()],
                userMessage = Random.nextBoolean()
            )
            dialogs.add(0, message)
        }
        return dialogs
    }

    private val textMessagesList = mutableListOf(
        "Привет! Как дела? ",
        "Когда будет готов отчет ",
        "Пойдешь сегодня гулять? ",
        "Даже не знаю ",
        "Когда в отпуск? ",
        "Что думаешь о погоде? ",
        "Я вот даже и не знаю... ",
        "Давай погнали гулять ",
        "Насчет того разговора...проехали ",
        "Ну ладно, сорян ",
        "Почитай еще чего нибудь ",
        "Смотрел Хатико? ",
        "Тебе понравился мюзикл? ",
        "Как тебе фильм о кольце и братстве? ",
        "Какая сортировка тебе по душе? ",
        "Может на великах покатаемся ",
        "Может спортом заняться... ",
        "Может новый диван купить ",
        "Ты обиделся? ",
        "Звездные войны или я? ",
        "Скорее бы на дачу "
    )

}