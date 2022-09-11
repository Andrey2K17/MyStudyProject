package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson12.JokeDataFetcher
import ru.pg13.mystudyproject.lessons.lesson8.models.Joke

interface CacheDataSource : JokeDataFetcher<Joke, Unit>, ChangeJokeStatus

interface ChangeJokeStatus {
    suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel
}