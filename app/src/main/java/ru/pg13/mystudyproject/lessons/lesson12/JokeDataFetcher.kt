package ru.pg13.mystudyproject.lessons.lesson12

import ru.pg13.mystudyproject.lessons.lesson11.Result

interface JokeDataFetcher<S, E> {
    suspend fun getJoke(): Result<S, E>
}