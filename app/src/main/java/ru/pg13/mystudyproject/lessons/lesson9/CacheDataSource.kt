package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel

interface CacheDataSource {

    fun addOrRemove(id: Int, joke: JokeServerModel): Joke

    fun getJoke(jokeCachedCallback: JokeCachedCallback)
}