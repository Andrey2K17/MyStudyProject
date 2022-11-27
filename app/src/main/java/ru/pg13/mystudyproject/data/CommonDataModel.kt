package ru.pg13.mystudyproject.data

import ru.pg13.mystudyproject.core.data.net.ChangeCommonItem
import ru.pg13.mystudyproject.core.data.net.ChangeStatus
import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper
import ru.pg13.mystudyproject.presentation.ShowText

class CommonDataModel<E>(
     private val id: E,
     private val text: String,
     private val cached: Boolean = false
) : ChangeCommonItem<E> {

     fun <T>map(mapper: CommonDataModelMapper<T, E>) : T {
          return mapper.map(id, text, cached)
     }

     fun map(showText: ShowText) = showText.show(text)

     override suspend fun change(changeStatus: ChangeStatus<E>) = changeStatus.addOrRemove(id, this)

     fun changeCached(cached: Boolean) = CommonDataModel(id, text, cached)
}