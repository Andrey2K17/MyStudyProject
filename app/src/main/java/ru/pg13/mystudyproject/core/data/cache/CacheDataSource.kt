package ru.pg13.mystudyproject.core.data.cache

import ru.pg13.mystudyproject.core.data.net.ChangeStatus
import ru.pg13.mystudyproject.core.data.net.DataFetcher
import ru.pg13.mystudyproject.data.CommonDataModel


interface CacheDataSource<E> : DataFetcher<E>, ChangeStatus<E> {
    suspend fun getDataList(): List<CommonDataModel<E>>
    suspend fun remove(id: E)
}