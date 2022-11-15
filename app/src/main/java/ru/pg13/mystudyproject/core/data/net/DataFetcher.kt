package ru.pg13.mystudyproject.core.data.net

import ru.pg13.mystudyproject.data.CommonDataModel

interface DataFetcher {
    suspend fun getData(): CommonDataModel
}