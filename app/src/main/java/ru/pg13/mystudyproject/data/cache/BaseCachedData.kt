package ru.pg13.mystudyproject.data.cache

import ru.pg13.mystudyproject.core.data.cache.CachedData
import ru.pg13.mystudyproject.core.data.net.ChangeCommonItem
import ru.pg13.mystudyproject.core.data.net.ChangeStatus
import ru.pg13.mystudyproject.data.CommonDataModel

class BaseCachedData<E>: CachedData<E> {
    private var cached: ChangeCommonItem<E> = ChangeCommonItem.Empty()

    override fun save(data: CommonDataModel<E>) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
        return cached.change(changeStatus)
    }
}