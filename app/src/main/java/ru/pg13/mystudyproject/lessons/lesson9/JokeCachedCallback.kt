package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel

interface JokeCachedCallback {

    fun provide(jokeServerModel: JokeServerModel)

    fun fail()
}