package ru.pg13.mystudyproject.presentation

import androidx.annotation.StringRes
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.core.ResourceManager
import ru.pg13.mystudyproject.core.presentation.Failure

abstract class BaseFailure(private val resourceManager: ResourceManager) : Failure {
    @StringRes
    protected abstract fun getMessageResId(): Int
    override fun getMessage() =
        resourceManager.getString(getMessageResId())
}

class NoConnection(resourceManager: ResourceManager): BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_connection
}

class ServiceUnavailable(resourceManager: ResourceManager): BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.service_is_unavailable
}

class NoCachedData(resourceManager: ResourceManager): BaseFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_cached_data
}