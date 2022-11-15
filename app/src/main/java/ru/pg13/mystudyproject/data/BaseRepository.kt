package ru.pg13.mystudyproject.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pg13.mystudyproject.core.data.cache.CacheDataSource
import ru.pg13.mystudyproject.core.data.cache.CachedData
import ru.pg13.mystudyproject.core.data.net.CloudDataSource
import ru.pg13.mystudyproject.core.data.net.CommonRepository
import ru.pg13.mystudyproject.core.data.net.DataFetcher


class BaseRepository(
    private val cacheDataSource: CacheDataSource,
    private val cloudDataSource: CloudDataSource,
    private val cached: CachedData
) : CommonRepository {

    private var currentDataSource: DataFetcher = cloudDataSource

    override fun chooseDataSource(cached: Boolean) {
        currentDataSource = if (cached) cacheDataSource else cloudDataSource
    }

    override suspend fun getCommonItem(): CommonDataModel = withContext(Dispatchers.IO) {
        try {
            val data = currentDataSource.getData()
            cached.save(data)
            return@withContext data
        } catch (e: Exception) {
            cached.clear()
            throw e
        }
    }

    override suspend fun changeStatus(): CommonDataModel = cached.change(cacheDataSource)
}