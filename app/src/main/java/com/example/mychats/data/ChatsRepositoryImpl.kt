package com.example.mychats.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mychats.domain.ChatData
import com.example.mychats.domain.ChatsRepository
import com.github.javafaker.Faker
import kotlin.random.Random

object ChatsRepositoryImpl : ChatsRepository {

    private val faker: Faker = Faker.instance()


    private val IMAGES = mutableListOf(
        "https://images.unsplash.com/photo-1610216705422-caa3fcb6d158?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1484684096794-03e03b5e713e?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1479936343636-73cdc5aae0c3?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1527980965255-d3b416303d12?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1557331467-f17b71e12ac8?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1570211776086-5836c8b1e75f?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1624344965182-f659bb1f510a?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1646470350098-7966d0486d7d?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=822",
        "https://images.unsplash.com/photo-1640705984507-ae77ff639996?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=895",
        "https://images.unsplash.com/photo-1522260448087-a56a0fd5282e?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=926",
        "https://images.unsplash.com/photo-1586490915014-b2490ffd4727?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1509195605820-8eb07b921387?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1517213849290-bbbfffdc6da3?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1522120657009-060ca01afcd6?ixlib=rb-1.2.1&raw_url=true&q=80&fm=jpg&crop=entropy&cs=tinysrgb&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=929",
        "https://images.unsplash.com/photo-1630409332816-aed015b9c6c2?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1636370792189-3f288f79cf2c?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1643224846380-48cfe1c7e6bc?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=884",
        "https://images.unsplash.com/photo-1636043235562-b4b316c53660?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880",
        "https://images.unsplash.com/photo-1564539279948-9931a76c05be?crop=entropy&cs=tinysrgb&fm=jpg&ixlib=rb-1.2.1&q=80&raw_url=true&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=880"
    )

    private var chats = mutableListOf<ChatData>()

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun deleteMessage(message: String) {
        chats.removeIf { it.text == message }
    }


}