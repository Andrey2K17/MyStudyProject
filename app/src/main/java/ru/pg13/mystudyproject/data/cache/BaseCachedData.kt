package ru.pg13.mystudyproject.data.cache

import ru.pg13.mystudyproject.core.data.cache.CachedData
import ru.pg13.mystudyproject.core.data.net.ChangeCommonItem
import ru.pg13.mystudyproject.core.data.net.ChangeStatus
import ru.pg13.mystudyproject.data.CommonDataModel

class BaseCachedData: CachedData {
    private var cached: ChangeCommonItem = ChangeCommonItem.Empty()

    override fun save(data: CommonDataModel) {
        cached = data
    }

    override fun clear() {
        cached = ChangeCommonItem.Empty()
    }

    override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
        return cached.change(changeStatus)
    }
}