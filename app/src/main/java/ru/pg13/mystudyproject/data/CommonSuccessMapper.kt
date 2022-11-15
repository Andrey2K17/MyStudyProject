package ru.pg13.mystudyproject.data

import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper
import ru.pg13.mystudyproject.domain.CommonItem

class CommonSuccessMapper : CommonDataModelMapper<CommonItem.Success> {
    override fun map(id: Int, text: String, cached: Boolean) =
        CommonItem.Success(text, cached)
}