package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson8.models.Joke

interface CloudDataSource {
    fun getJoke(callback: JokeCloudCallback)
}

interface JokeCloudCallback {
    fun provide(joke: Joke)
    fun fail(error: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}