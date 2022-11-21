package ru.pg13.mystudyproject.core.data.cache

import ru.pg13.mystudyproject.data.CommonDataModel
import ru.pg13.mystudyproject.core.data.net.ChangeCommonItem

interface CachedData<E> : ChangeCommonItem<E> {
    fun save(data: CommonDataModel<E>)
    fun clear()
}