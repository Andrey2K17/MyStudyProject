package ru.pg13.mystudyproject.domain

import ru.pg13.mystudyproject.data.JokeRealmModel

interface JokeDataModelMapper<T> {
    fun map(id: Int, text: String, cached: Boolean) : T
}

class JokeSuccessMapper : JokeDataModelMapper<Joke.Success> {
    override fun map(id: Int, text: String, cached: Boolean) =
        Joke.Success(text, cached)
}

class JokeRealmMapper : JokeDataModelMapper<JokeRealmModel> {
    override fun map(id: Int, text: String, cached: Boolean): JokeRealmModel {
        return JokeRealmModel().also { joke ->
            joke.id = id
            joke.text = text
        }
    }

}