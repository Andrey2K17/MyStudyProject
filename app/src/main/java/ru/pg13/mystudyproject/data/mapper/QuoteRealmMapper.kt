package ru.pg13.mystudyproject.data.mapper

import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper
import ru.pg13.mystudyproject.data.cache.QuoteRealmModel

class QuoteRealmMapper : CommonDataModelMapper<QuoteRealmModel> {
    override fun map(id: Int, text: String, cached: Boolean) = QuoteRealmModel().also { joke ->
        joke.id = id
        joke.text = text
    }
}