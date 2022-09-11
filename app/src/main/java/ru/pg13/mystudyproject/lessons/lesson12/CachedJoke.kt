package ru.pg13.mystudyproject.lessons.lesson12

import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson9.ChangeJokeStatus
import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: Joke)
    fun clear()
}

interface ChangeJoke {
    suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeUiModel?
}