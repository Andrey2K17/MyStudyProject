package ru.pg13.mystudyproject.lessons.lesson8.interfaces

import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

interface Model {
    suspend fun getJoke(): JokeUiModel
    suspend fun changeJokeStatus(): JokeUiModel?

    fun chooseDataSource(favorites: Boolean)
}