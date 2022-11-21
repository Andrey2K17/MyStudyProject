package ru.pg13.mystudyproject.data.mapper

import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper
import ru.pg13.mystudyproject.data.cache.JokeRealmModel

class JokeRealmMapper : CommonDataModelMapper<JokeRealmModel, Int> {
    override fun map(id: Int, text: String, cached: Boolean) = JokeRealmModel().also { joke ->
        joke.id = id
        joke.text = text
    }
}