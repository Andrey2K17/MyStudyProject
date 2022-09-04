package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel
import ru.pg13.mystudyproject.lessons.lesson8.models.Value

class TestCloudDataSource: CloudDataSource {
    private var count = 0
    override fun getJoke(callback: JokeCloudCallback) {
        callback.provide(JokeServerModel("success", Value(count, "joke$count", listOf())))
        count++
    }
}