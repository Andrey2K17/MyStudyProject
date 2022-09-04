package ru.pg13.mystudyproject.lessons.lesson8.interfaces

import ru.pg13.mystudyproject.lessons.lesson9.Joke

interface JokeCallback {

    fun provide(data: Joke)
}