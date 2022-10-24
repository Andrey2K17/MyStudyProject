package ru.pg13.mystudyproject.domain

import ru.pg13.mystudyproject.domain.interfaces.JokeFailure
import ru.pg13.mystudyproject.domain.interfaces.JokeFailureHandler
import ru.pg13.mystudyproject.ResourceManager


class JokeFailureFactory(private val resourceManager: ResourceManager): JokeFailureHandler {
    override fun handle(e: Exception): JokeFailure {
        return when (e) {
            is NoConnectionException -> NoConnection(resourceManager)
            is NoCachedJokesException -> NoCachedJokes(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }
}