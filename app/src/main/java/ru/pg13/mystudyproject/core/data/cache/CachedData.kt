package ru.pg13.mystudyproject.core.data.cache

import ru.pg13.mystudyproject.data.CommonDataModel
import ru.pg13.mystudyproject.core.data.net.ChangeCommonItem

interface CachedData : ChangeCommonItem {
    fun save(data: CommonDataModel)
    fun clear()
}