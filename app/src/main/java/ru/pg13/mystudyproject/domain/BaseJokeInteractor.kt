package ru.pg13.mystudyproject.domain

import android.util.Log
import ru.pg13.mystudyproject.data.interfaces.JokeRepository
import ru.pg13.mystudyproject.domain.interfaces.JokeFailureHandler
import ru.pg13.mystudyproject.domain.interfaces.JokeInteractor

class BaseJokeInteractor(
    private val repository: JokeRepository,
    private val jokeFailureHandler: JokeFailureHandler,
    private val mapper: JokeDataModelMapper<Joke.Success>
) : JokeInteractor {

    override suspend fun getJoke(): Joke {
        return try {
            val a = repository.getJoke().map(mapper)
            Log.d("test123", "a: $a")
            return a
        } catch (e: Exception) {
            Log.d("test123", "a: $e")
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): Joke {
        return try {
            repository.getJoke().map(mapper)
        } catch (e: Exception) {
            Joke.Failed(jokeFailureHandler.handle(e))
        }
    }

    override fun getFavoriteJoke(favorites: Boolean) =
        repository.chooseDataSource(favorites)
}