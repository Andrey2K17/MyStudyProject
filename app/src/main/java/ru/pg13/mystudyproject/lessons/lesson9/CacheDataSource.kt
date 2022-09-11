package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson11.Result
import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel

interface CacheDataSource {

    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel

    suspend fun getJoke() : Result<Joke, Unit>
}