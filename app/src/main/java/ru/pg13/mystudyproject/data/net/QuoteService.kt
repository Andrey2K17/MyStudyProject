package ru.pg13.mystudyproject.data.net

import retrofit2.Call
import retrofit2.http.GET

interface QuoteService {
    @GET("https://api.quotable.io/random")
    fun getQuote(): Call<QuoteServerModel>
}