package ru.pg13.mystudyproject.data

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pg13.mystudyproject.data.interfaces.*


class BaseJokeRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cachedJoke: CachedJoke
) : JokeRepository {

    private var currentDataSource: JokeDataFetcher = cloudDataSource

    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }

    override suspend fun getJoke(): JokeDataModel = withContext(Dispatchers.IO) {
        try {
            val joke = currentDataSource.getJoke()
            Log.d("test123", "joke: $joke")
            cachedJoke.saveJoke(joke)
            return@withContext joke
        } catch (e: Exception) {
            cachedJoke.clear()
            throw e
        }
    }

    override suspend fun changeJokeStatus(): JokeDataModel = cachedJoke.change(cacheDataSource)
}