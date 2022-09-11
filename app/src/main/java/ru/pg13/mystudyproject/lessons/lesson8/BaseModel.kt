package ru.pg13.mystudyproject.lessons.lesson8

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pg13.mystudyproject.lessons.lesson12.BaseResultHandler
import ru.pg13.mystudyproject.lessons.lesson12.CacheResultHandler
import ru.pg13.mystudyproject.lessons.lesson12.CachedJoke
import ru.pg13.mystudyproject.lessons.lesson12.CloudResultHandler
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.Model
import ru.pg13.mystudyproject.lessons.lesson9.CacheDataSource
import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

class BaseModel(
    private val cacheDataSource: CacheDataSource,
    private val cacheResultHandler: CacheResultHandler,
    private val cloudResultHandler: CloudResultHandler,
    private val cachedJoke: CachedJoke
) : Model {

    private var currentResultHandler: BaseResultHandler<*, *> = cloudResultHandler

    override suspend fun getJoke(): JokeUiModel = withContext(Dispatchers.IO) {
        return@withContext currentResultHandler.process()
    }

    override suspend fun changeJokeStatus(): JokeUiModel? = cachedJoke?.change(cacheDataSource)

    override fun chooseDataSource(cached: Boolean) {
        currentResultHandler = if (cached) cacheResultHandler else cloudResultHandler
    }
}