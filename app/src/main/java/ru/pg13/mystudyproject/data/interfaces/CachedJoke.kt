package ru.pg13.mystudyproject.data.interfaces

import ru.pg13.mystudyproject.data.JokeDataModel

interface CachedJoke : ChangeJoke {
    fun saveJoke(joke: JokeDataModel)
    fun clear()
}