package ru.pg13.mystudyproject.lessons.lesson12

import ru.pg13.mystudyproject.lessons.lesson11.Result
import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeFailure
import ru.pg13.mystudyproject.lessons.lesson9.FailedJokeUiModel

class CacheResultHandler(
    private val cachedJoke: CachedJoke,
    jokeDataFetcher: JokeDataFetcher<Joke, Unit>,
    private val noCachedJokes: JokeFailure
) :
    BaseResultHandler<Joke, Unit>(jokeDataFetcher) {
    override fun handleResult(result: Result<Joke, Unit>) = when (result) {
        is Result.Success<Joke> -> result.data.let {
            cachedJoke.saveJoke(it)
            it.toFavoriteJoke()
        }
        is Result.Error -> {
            cachedJoke.clear()
            FailedJokeUiModel(noCachedJokes.getMessage())
        }
    }
}