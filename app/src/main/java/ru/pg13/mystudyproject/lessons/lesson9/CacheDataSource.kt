package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel

interface CacheDataSource {

    fun addOrRemove(id: Int, joke: Joke): JokeUiModel

    fun getJoke(jokeCachedCallback: JokeCachedCallback)
}

interface JokeCachedCallback {

    fun provide(joke: Joke)

    fun fail()
}