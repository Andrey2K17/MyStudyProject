package ru.pg13.mystudyproject

import retrofit2.Retrofit
import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.core.domain.FailureHandler
import ru.pg13.mystudyproject.data.BaseRepository
import ru.pg13.mystudyproject.data.CommonSuccessMapper
import ru.pg13.mystudyproject.data.cache.BaseCachedData
import ru.pg13.mystudyproject.data.cache.QuoteCachedDataSource
import ru.pg13.mystudyproject.data.cache.QuoteRealmToCommonMapper
import ru.pg13.mystudyproject.data.mapper.QuoteRealmMapper
import ru.pg13.mystudyproject.data.net.QuoteCloudDataSource
import ru.pg13.mystudyproject.data.net.QuoteService
import ru.pg13.mystudyproject.domain.BaseInteractor
import ru.pg13.mystudyproject.presentation.BaseCommunication
import ru.pg13.mystudyproject.presentation.QuotesViewModel

class QuotesModule(
    private val failureHandler: FailureHandler,
    private val realmProvider: RealmProvider,
    private val retrofit: Retrofit
) : Module.Base<String, QuotesViewModel>() {

    private var communication: BaseCommunication<String>? = null

    override fun getCommunications(): BaseCommunication<String> {
        if (communication == null)
            communication = BaseCommunication()
        return communication!!
    }

    override fun getViewModel() = QuotesViewModel(getInteractor(), getCommunications())

    private fun getInteractor() =
        BaseInteractor(getRepository(), failureHandler, CommonSuccessMapper())

    private fun getRepository() = BaseRepository(
        getCacheDataSource(),
        getCloudDataSource(),
        BaseCachedData()
    )

    private fun getCacheDataSource() =
        QuoteCachedDataSource(realmProvider, QuoteRealmMapper(), QuoteRealmToCommonMapper())

    private fun getCloudDataSource() =
        QuoteCloudDataSource(retrofit.create(QuoteService::class.java))
}