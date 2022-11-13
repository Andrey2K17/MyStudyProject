package ru.pg13.mystudyproject.data

import com.google.gson.annotations.SerializedName
import ru.pg13.mystudyproject.core.Mapper


data class JokeServerModel(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("type")
    private val type: String,
    @SerializedName("setup")
    private val setup: String,
    @SerializedName("punchline")
    private val punchline: String,
) : Mapper<JokeDataModel> {

    override fun to() = JokeDataModel(id, "$setup:::$punchline")
}

//data class JokeServerModel(
//    @SerializedName("type")
//    private val type: String,
//    @SerializedName("value")
//    private val value: Value,
//) : Mapper<JokeDataModel> {
//
//    override fun to() = JokeDataModel(value.id, value.joke)
//}
//
//data class Value(
//    @SerializedName("id")
//    val id: Int,
//    @SerializedName("joke")
//    val joke: String,
//    @SerializedName("categories")
//    private val categories: List<String>,
//)