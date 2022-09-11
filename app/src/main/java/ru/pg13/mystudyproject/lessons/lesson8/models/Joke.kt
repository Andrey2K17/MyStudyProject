package ru.pg13.mystudyproject.lessons.lesson8.models

import com.google.gson.annotations.SerializedName
import ru.pg13.mystudyproject.lessons.lesson9.BaseJokeUiModel
import ru.pg13.mystudyproject.lessons.lesson9.CacheDataSource
import ru.pg13.mystudyproject.lessons.lesson9.DB.JokeRealm
import ru.pg13.mystudyproject.lessons.lesson9.FavoriteJokeUiModel

data class Joke(
    @SerializedName("type")
    private val type: String,
    @SerializedName("value")
    private val value: Value,
) {
    fun toBaseJoke() = BaseJokeUiModel(value.joke)

    fun toFavoriteJoke() = FavoriteJokeUiModel(value.joke)

    fun toJokeRealm(): JokeRealm {
        return JokeRealm().also {
            it.id = value.id
            it.type = type
            it.text = value.joke
        }
    }

    suspend fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(value.id, this)
}