package ru.pg13.mystudyproject.data.interfaces

import ru.pg13.mystudyproject.data.JokeDataModel

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: JokeDataModel): JokeDataModel
}