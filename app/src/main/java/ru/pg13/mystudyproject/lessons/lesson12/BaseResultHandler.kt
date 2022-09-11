package ru.pg13.mystudyproject.lessons.lesson12

import ru.pg13.mystudyproject.lessons.lesson11.Result
import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

abstract class BaseResultHandler<S, E>
    (private val jokeDataFetcher: JokeDataFetcher<S, E>) {

    suspend fun process(): JokeUiModel {
        return handleResult(jokeDataFetcher.getJoke())
    }

    protected abstract fun handleResult(result: Result<S, E>): JokeUiModel
}