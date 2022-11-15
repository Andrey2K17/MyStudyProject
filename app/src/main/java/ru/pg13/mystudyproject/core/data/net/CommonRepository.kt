package ru.pg13.mystudyproject.core.data.net

import ru.pg13.mystudyproject.data.CommonDataModel

interface CommonRepository {
    suspend fun getCommonItem(): CommonDataModel
    suspend fun changeStatus(): CommonDataModel

    fun chooseDataSource(favorites: Boolean)
}