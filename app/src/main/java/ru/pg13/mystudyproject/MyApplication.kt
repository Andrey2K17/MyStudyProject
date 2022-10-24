package ru.pg13.mystudyproject

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pg13.mystudyproject.data.BaseCachedDataSource
import ru.pg13.mystudyproject.data.BaseCloudDataSource
import ru.pg13.mystudyproject.data.BaseRealmProvider
import ru.pg13.mystudyproject.data.interfaces.JokeService
import ru.pg13.mystudyproject.domain.BaseJokeInteractor
import ru.pg13.mystudyproject.domain.JokeFailureFactory
import ru.pg13.mystudyproject.domain.JokeRealmMapper
import ru.pg13.mystudyproject.domain.JokeSuccessMapper
import ru.pg13.mystudyproject.data.BaseCachedJoke
import ru.pg13.mystudyproject.domain.BaseCommunication
import ru.pg13.mystudyproject.domain.BaseViewModel
import ru.pg13.mystudyproject.data.BaseJokeRepository

class MyApplication : Application() {

    lateinit var viewModel: BaseViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cacheDataSource = BaseCachedDataSource(BaseRealmProvider(), JokeRealmMapper())
        val resourceManager = BaseResourceManager(this)
        val cloudDataSource = BaseCloudDataSource(retrofit.create(JokeService::class.java))
        val repository = BaseJokeRepository(cacheDataSource, cloudDataSource, BaseCachedJoke())
        val interactor = BaseJokeInteractor(repository, JokeFailureFactory(resourceManager), JokeSuccessMapper())
        viewModel = BaseViewModel(interactor, BaseCommunication())
    }
}