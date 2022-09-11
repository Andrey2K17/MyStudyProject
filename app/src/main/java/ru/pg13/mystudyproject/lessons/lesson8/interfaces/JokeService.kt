package ru.pg13.mystudyproject.lessons.lesson8.interfaces

import retrofit2.Call
import retrofit2.http.GET
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel

interface JokeService {

//    @GET("http://api.icndb.com/jokes/random/")
//    suspend fun getJoke(): JokeServerModel

    @GET("http://api.icndb.com/jokes/random/")
    fun getJoke(): Call<JokeServerModel>
}


