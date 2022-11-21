package ru.pg13.mystudyproject.data.net

import com.google.gson.annotations.SerializedName
import ru.pg13.mystudyproject.core.Mapper
import ru.pg13.mystudyproject.data.CommonDataModel

class QuoteServerModel(
    @SerializedName("_id")
    private val id: String,
    @SerializedName("content")
    private val content: String,
    @SerializedName("author")
    private val author: String
) : Mapper<CommonDataModel<String>> {
    override fun to() = CommonDataModel(id, "$content:::$author")
}