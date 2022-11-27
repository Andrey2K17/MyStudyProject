package ru.pg13.mystudyproject.core.data.net

import ru.pg13.mystudyproject.data.CommonDataModel

interface CommonRepository<E> {
    suspend fun getCommonItem(): CommonDataModel<E>
    suspend fun getCommonItemList(): List<CommonDataModel<E>>
    suspend fun changeStatus(): CommonDataModel<E>

    fun chooseDataSource(favorites: Boolean)
}