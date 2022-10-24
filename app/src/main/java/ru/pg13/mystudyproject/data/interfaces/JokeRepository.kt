package ru.pg13.mystudyproject.data.interfaces

import ru.pg13.mystudyproject.data.JokeDataModel

interface JokeRepository {
    suspend fun getJoke(): JokeDataModel
    suspend fun changeJokeStatus(): JokeDataModel?

    fun chooseDataSource(favorites: Boolean)
}