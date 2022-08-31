package ru.pg13.mystudyproject.lessons.lesson8.interfaces

import ru.pg13.mystudyproject.lessons.lesson8.models.JokeDTO

interface JokeService {

    fun getJoke(callback: ServiceCallback)
}

interface ServiceCallback {

    fun returnSuccess(data: JokeDTO)

    fun returnError(type: ErrorType)
}

enum class ErrorType {
    NO_CONNECTION,
    OTHER
}


