package ru.pg13.mystudyproject.data

import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper
import ru.pg13.mystudyproject.domain.CommonItem

class CommonSuccessMapper<E> : CommonDataModelMapper<CommonItem.Success<E>, E> {
    override fun map(id: E, text: String, cached: Boolean) =
        CommonItem.Success(id, text, cached)
}