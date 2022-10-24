package ru.pg13.mystudyproject.domain.interfaces


interface JokeFailureHandler {
    fun handle(e: Exception): JokeFailure
}