package ru.pg13.mystudyproject.lessons.lesson8

import android.util.Log
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

    private var jokeCallback: JokeCallback? = null
    private var cachedJoke: Joke? = null
    private var getJokeFromCache = false

    override fun getJoke() {
        Log.d("test123", "get: $getJokeFromCache")
        if (getJokeFromCache) {
            cacheDataSource.getJoke(object : JokeCachedCallback {
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toFavoriteJoke())
                }

                override fun fail() {
                    cachedJoke = null
                    jokeCallback?.provide(FailedJokeUiModel(noCachedJoke.getMessage()))
                }
            })
        } else {
            cloudDataSource.getJoke(object : JokeCloudCallback {
                override fun provide(joke: Joke) {
                    cachedJoke = joke
                    jokeCallback?.provide(joke.toBaseJoke())
                }

                override fun fail(error: ErrorType) {
                    cachedJoke = null
                    val failure = if (error == ErrorType.NO_CONNECTION) noConnection else serviceUnavailable
                    jokeCallback?.provide(FailedJokeUiModel(failure.getMessage()))
                }

            })
        }
    }

    override fun init(callback: JokeCallback) {
        this.jokeCallback = callback
    }

    override fun clear() {
        jokeCallback = null
    }

    override fun changeJokeStatus(jokeCallback: JokeCallback) {
        cachedJoke?.change(cacheDataSource)?.let {
            jokeCallback.provide(it)
        }
    }

    override fun chooseDataSource(cached: Boolean) {
        getJokeFromCache = cached
    }
}