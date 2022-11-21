package ru.pg13.mystudyproject.domain

import android.util.Log
import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper
import ru.pg13.mystudyproject.core.data.net.CommonRepository
import ru.pg13.mystudyproject.core.domain.CommonInteractor
import ru.pg13.mystudyproject.core.domain.FailureHandler

class BaseInteractor<E>(
    private val repository: CommonRepository<E>,
    private val jokeFailureHandler: FailureHandler,
    private val mapper: CommonDataModelMapper<CommonItem.Success, E>
) : CommonInteractor {

    override suspend fun getItem(): CommonItem {
        return try {
            repository.getCommonItem().map(mapper)
        } catch (e: Exception) {
            CommonItem.Failed(jokeFailureHandler.handle(e))
        }
    }

    override suspend fun changeFavorites(): CommonItem {
        return try {
            repository.changeStatus().map(mapper)
        } catch (e: Exception) {
            Log.d("test123", "exception: $e")
            CommonItem.Failed(jokeFailureHandler.handle(e))
        }
    }

    override fun getFavoriteJoke(favorites: Boolean) =
        repository.chooseDataSource(favorites)
}