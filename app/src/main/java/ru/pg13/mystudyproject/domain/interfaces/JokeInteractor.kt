package ru.pg13.mystudyproject.domain.interfaces

import ru.pg13.mystudyproject.domain.Joke

interface JokeInteractor {

    suspend fun getJoke() : Joke

    suspend fun changeFavorites() : Joke

    fun getFavoriteJoke(favorites: Boolean)
}