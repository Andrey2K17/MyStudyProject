package ru.pg13.mystudyproject.lessons.lesson8.interfaces

import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeFailure

interface ResultCallback {

    fun provideSuccess(data: Joke)

    fun provideError(error: JokeFailure)
}