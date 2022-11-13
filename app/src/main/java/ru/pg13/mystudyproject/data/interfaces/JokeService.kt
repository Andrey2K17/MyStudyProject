package ru.pg13.mystudyproject.data.interfaces

import retrofit2.Call
import retrofit2.http.GET
import ru.pg13.mystudyproject.data.JokeServerModel

interface JokeService {

//    @GET("http://api.icndb.com/jokes/random/")
//    suspend fun getJoke(): JokeServerModel

/*    @GET("http://api.icndb.com/jokes/random/")
    fun getJoke(): Call<JokeServerModel>*/

    @GET("https://official-joke-api.appspot.com/random_joke")
    fun getJoke(): Call<JokeServerModel>
}


