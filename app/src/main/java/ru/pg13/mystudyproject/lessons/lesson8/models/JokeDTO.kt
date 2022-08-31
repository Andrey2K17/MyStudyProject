package ru.pg13.mystudyproject.lessons.lesson8.models

import com.google.gson.annotations.SerializedName

data class JokeDTO(
    @SerializedName("type")
    private val type: String,
    @SerializedName("value")
    private val value: Value,
) {
    fun toJoke() = Joke(value.joke)
}

data class Value(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("joke")
    val joke: String,
    @SerializedName("categories")
    private val categories: List<String>,
)