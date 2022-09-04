package ru.pg13.mystudyproject.lessons.lesson8.models

import com.google.gson.annotations.SerializedName
import ru.pg13.mystudyproject.lessons.lesson9.BaseJoke
import ru.pg13.mystudyproject.lessons.lesson9.CacheDataSource
import ru.pg13.mystudyproject.lessons.lesson9.FavoriteJoke

data class JokeServerModel(
    @SerializedName("type")
    private val type: String,
    @SerializedName("value")
    private val value: Value,
) {
    fun toBaseJoke() = BaseJoke(value.joke)

    fun toFavoriteJoke() = FavoriteJoke(value.joke)

    fun change(cacheDataSource: CacheDataSource) = cacheDataSource.addOrRemove(value.id, this)
}

data class Value(
    @SerializedName("id")
    val id: Int,
    @SerializedName("joke")
    val joke: String,
    @SerializedName("categories")
    private val categories: List<String>,
)