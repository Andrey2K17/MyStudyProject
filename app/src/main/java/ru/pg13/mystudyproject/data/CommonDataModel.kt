package ru.pg13.mystudyproject.data

import ru.pg13.mystudyproject.core.data.net.ChangeCommonItem
import ru.pg13.mystudyproject.core.data.net.ChangeStatus
import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper

class CommonDataModel(
     private val id: Int,
     private val text: String,
     private val cached: Boolean = false
) : ChangeCommonItem {

     fun <T>map(mapper: CommonDataModelMapper<T>) : T {
          return mapper.map(id, text, cached)
     }

     override suspend fun change(changeStatus: ChangeStatus) = changeStatus.addOrRemove(id, this)

     fun changeCached(cached: Boolean) = CommonDataModel(id, text, cached)
}