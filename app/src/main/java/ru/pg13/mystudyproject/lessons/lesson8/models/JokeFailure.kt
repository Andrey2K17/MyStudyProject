package ru.pg13.mystudyproject.lessons.lesson8.models

import ru.pg13.mystudyproject.R

interface JokeFailure {
    fun getMessage(): String
}

class NoConnection(private val resourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.no_connection)
}

class ServiceUnavailable(private val resourceManager: ResourceManager): JokeFailure {
    override fun getMessage(): String = resourceManager.getString(R.string.service_is_unavailable)

}