package ru.pg13.mystudyproject.domain

import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.domain.interfaces.JokeFailure
import ru.pg13.mystudyproject.ResourceManager

class GenericError(private val resourceManager: ResourceManager) : JokeFailure {
    override fun getMessage() = resourceManager.getString(R.string.generic_fail_message)
}