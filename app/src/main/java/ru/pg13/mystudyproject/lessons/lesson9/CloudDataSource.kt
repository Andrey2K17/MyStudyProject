package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson12.JokeDataFetcher
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel

interface CloudDataSource : JokeDataFetcher<JokeServerModel, ErrorType>

enum class ErrorType {
    NO_CONNECTION,
    SERVICE_UNAVAILABLE
}