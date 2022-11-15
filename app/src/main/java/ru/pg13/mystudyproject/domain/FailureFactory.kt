package ru.pg13.mystudyproject.domain

import ru.pg13.mystudyproject.core.ResourceManager
import ru.pg13.mystudyproject.core.domain.FailureHandler
import ru.pg13.mystudyproject.core.domain.NoCachedDataException
import ru.pg13.mystudyproject.core.domain.NoConnectionException
import ru.pg13.mystudyproject.core.domain.ServiceUnavailableException
import ru.pg13.mystudyproject.core.presentation.Failure
import ru.pg13.mystudyproject.presentation.NoCachedData
import ru.pg13.mystudyproject.presentation.NoConnection
import ru.pg13.mystudyproject.presentation.ServiceUnavailable


class FailureFactory(private val resourceManager: ResourceManager): FailureHandler {
    override fun handle(e: Exception): Failure {
        return when (e) {
            is NoConnectionException -> NoConnection(resourceManager)
            is NoCachedDataException -> NoCachedData(resourceManager)
            is ServiceUnavailableException -> ServiceUnavailable(resourceManager)
            else -> GenericError(resourceManager)
        }
    }
}