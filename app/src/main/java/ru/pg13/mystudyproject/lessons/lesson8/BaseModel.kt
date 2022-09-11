package ru.pg13.mystudyproject.lessons.lesson8

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pg13.mystudyproject.lessons.lesson11.Result
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.*
import ru.pg13.mystudyproject.lessons.lesson8.models.*
import ru.pg13.mystudyproject.lessons.lesson9.*
import ru.pg13.mystudyproject.lessons.lesson9.ErrorType

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val resourceManager: ResourceManager
) : Model {
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }
    private val noCachedJoke by lazy { NoCachedJokes(resourceManager) }

    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false

    override suspend fun getJoke(): JokeUiModel = withContext(Dispatchers.IO) {
        Log.d("test123", "get: $getJokeFromCache")
        if (getJokeFromCache) {
            return@withContext when (val result = cacheDataSource.getJoke()) {
                is Result.Success<Joke> -> result.data.let {
                    cachedJoke = it
                    it.toFavoriteJoke()
                }
                is Result.Error -> {
                    cachedJoke = null
                    FailedJokeUiModel(noCachedJoke.getMessage())
                }
            }
        } else {
            return@withContext when (val result = cloudDataSource.getJoke()) {
                is Result.Success<JokeServerModel> -> {
                    result.data.toJoke().let {
                        cachedJoke = it
                        it.toBaseJoke()
                    }
                }
                is Result.Error<ErrorType> -> {
                    cachedJoke = null
                    val failure = if (result.exception == ErrorType.NO_CONNECTION)
                        noConnection
                    else serviceUnavailable
                    FailedJokeUiModel(failure.getMessage())
                }
            }
        }
    }

    override suspend fun changeJokeStatus(): JokeUiModel? = cachedJoke?.change(cacheDataSource)

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }
}