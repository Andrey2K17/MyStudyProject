package ru.pg13.mystudyproject.domain

import androidx.annotation.StringRes
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.domain.interfaces.JokeFailure
import ru.pg13.mystudyproject.ResourceManager

abstract class BaseJokeFailure(private val resourceManager: ResourceManager) : JokeFailure {
    @StringRes
    protected abstract fun getMessageResId(): Int
    override fun getMessage() =
        resourceManager.getString(getMessageResId())
}

class NoConnection(resourceManager: ResourceManager): BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_connection
}

class ServiceUnavailable(resourceManager: ResourceManager): BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.service_is_unavailable
}

class NoCachedJokes(resourceManager: ResourceManager): BaseJokeFailure(resourceManager) {
    override fun getMessageResId() = R.string.no_cached_jokes
}