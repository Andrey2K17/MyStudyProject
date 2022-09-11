package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson11.Result
import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel

interface CloudDataSource {
    suspend fun getJoke(): Result<JokeServerModel, ErrorType>
}

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}