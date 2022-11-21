package ru.pg13.mystudyproject

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pg13.mystudyproject.data.BaseRepository
import ru.pg13.mystudyproject.data.CommonSuccessMapper
import ru.pg13.mystudyproject.data.cache.*
import ru.pg13.mystudyproject.data.mapper.JokeRealmMapper
import ru.pg13.mystudyproject.data.mapper.QuoteRealmMapper
import ru.pg13.mystudyproject.data.net.JokeCloudDataSource
import ru.pg13.mystudyproject.data.net.JokeService
import ru.pg13.mystudyproject.data.net.QuoteCloudDataSource
import ru.pg13.mystudyproject.data.net.QuoteService
import ru.pg13.mystudyproject.domain.BaseInteractor
import ru.pg13.mystudyproject.domain.FailureFactory
import ru.pg13.mystudyproject.presentation.BaseCommunication
import ru.pg13.mystudyproject.presentation.BaseResourceManager
import ru.pg13.mystudyproject.presentation.BaseViewModel

class MyApplication : Application() {

    lateinit var viewModel: BaseViewModel
    lateinit var quoteViewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val realProvider = BaseRealmProvider()
        val cacheDataSource = JokeCachedDataSource(realProvider, JokeRealmMapper(), JokeRealmToCommonMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource = JokeCloudDataSource(retrofit.create(JokeService::class.java))
        val repository = BaseRepository(cacheDataSource, cloudDataSource, BaseCachedData())
        val failureHandler = FailureFactory(resourceManager)
        val jokeMapper = CommonSuccessMapper<Int>()
        val interactor = BaseInteractor(repository, failureHandler, jokeMapper)
        viewModel = BaseViewModel(interactor, BaseCommunication())

        val quoteRepository = BaseRepository(
            QuoteCachedDataSource(realProvider, QuoteRealmMapper(), QuoteRealmToCommonMapper()),
            QuoteCloudDataSource(retrofit.create(QuoteService::class.java)),
            BaseCachedData()
        )
        val quoteMapper = CommonSuccessMapper<String>()
        quoteViewModel = BaseViewModel(
            BaseInteractor(quoteRepository, failureHandler, quoteMapper),
            BaseCommunication()
        )
    }
}