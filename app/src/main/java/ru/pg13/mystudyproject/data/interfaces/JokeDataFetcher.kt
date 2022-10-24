package ru.pg13.mystudyproject.data.interfaces

import ru.pg13.mystudyproject.data.JokeDataModel

interface JokeDataFetcher {
    suspend fun getJoke(): JokeDataModel
}