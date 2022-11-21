package ru.pg13.mystudyproject.core.data.net

import ru.pg13.mystudyproject.data.CommonDataModel

interface DataFetcher<E> {
    suspend fun getData(): CommonDataModel<E>
}