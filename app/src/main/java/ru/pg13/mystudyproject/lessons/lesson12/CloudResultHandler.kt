package ru.pg13.mystudyproject.lessons.lesson12

import ru.pg13.mystudyproject.lessons.lesson11.Result
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeFailure
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel
import ru.pg13.mystudyproject.lessons.lesson9.ErrorType
import ru.pg13.mystudyproject.lessons.lesson9.FailedJokeUiModel
import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

class CloudResultHandler(
    private val cachedJoke: CachedJoke,
    jokeDataFetcher: JokeDataFetcher<JokeServerModel, ErrorType>,
    private val noConnection: JokeFailure,
    private val serviceUnavailable: JokeFailure
) :
    BaseResultHandler<JokeServerModel, ErrorType>(jokeDataFetcher) {
    override fun handleResult(result: Result<JokeServerModel, ErrorType>): JokeUiModel =
        when (result) {
            is Result.Success<JokeServerModel> -> {
                result.data.toJoke().let {
                    cachedJoke.saveJoke(it)
                    it.toBaseJoke()
                }
            }
            is Result.Error<ErrorType> -> {
                cachedJoke.clear()
                val failure = if (result.exception == ErrorType.NO_CONNECTION)
                    noConnection
                else serviceUnavailable
                FailedJokeUiModel(failure.getMessage())
            }
        }
}