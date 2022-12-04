package ru.pg13.mystudyproject

import retrofit2.Retrofit
import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.core.domain.FailureHandler
import ru.pg13.mystudyproject.data.BaseRepository
import ru.pg13.mystudyproject.data.CommonSuccessMapper
import ru.pg13.mystudyproject.data.cache.BaseCachedData
import ru.pg13.mystudyproject.data.cache.JokeCachedDataSource
import ru.pg13.mystudyproject.data.cache.JokeRealmToCommonMapper
import ru.pg13.mystudyproject.data.mapper.JokeRealmMapper
import ru.pg13.mystudyproject.data.net.JokeCloudDataSource
import ru.pg13.mystudyproject.data.net.JokeService
import ru.pg13.mystudyproject.domain.BaseInteractor
import ru.pg13.mystudyproject.presentation.BaseCommunication
import ru.pg13.mystudyproject.presentation.JokesViewModel

class JokesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
): Module.Base<Int, JokesViewModel>() {

    private var communication: BaseCommunication<Int>? = null

    override fun getCommunications(): BaseCommunication<Int> {
        if (communication == null)
            communication = BaseCommunication()
        return communication!!
    }

    override fun getViewModel(): JokesViewModel {
        return JokesViewModel(getInteractor(), getCommunications())
    }

    private fun getInteractor() =
        BaseInteractor(getRepository(), failureHandler, CommonSuccessMapper())

    private fun getRepository() =
        BaseRepository(getCacheDataSource(), getCloudDataSource(), BaseCachedData())

    private fun getCacheDataSource() =
        JokeCachedDataSource(realmProvider, JokeRealmMapper(), JokeRealmToCommonMapper())

    private fun getCloudDataSource() =
        JokeCloudDataSource(retrofit.create(JokeService::class.java))
}