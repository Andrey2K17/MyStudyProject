package ru.pg13.mystudyproject.domain

import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.core.ResourceManager
import ru.pg13.mystudyproject.core.presentation.Failure

class GenericError(private val resourceManager: ResourceManager) : Failure {
    override fun getMessage() = resourceManager.getString(R.string.generic_fail_message)
}