package ru.pg13.mystudyproject.data.interfaces

import ru.pg13.mystudyproject.data.JokeDataModel
import java.lang.IllegalStateException

interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel

    class Empty : ChangeJoke {
        override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
            throw IllegalStateException("empty change joke called")
        }
    }
}