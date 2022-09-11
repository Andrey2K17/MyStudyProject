package ru.pg13.mystudyproject

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pg13.mystudyproject.lessons.lesson11.BaseRealmProvider
import ru.pg13.mystudyproject.lessons.lesson12.BaseCachedJoke
import ru.pg13.mystudyproject.lessons.lesson12.CacheResultHandler
import ru.pg13.mystudyproject.lessons.lesson12.CloudResultHandler
import ru.pg13.mystudyproject.lessons.lesson8.BaseModel
import ru.pg13.mystudyproject.lessons.lesson8.ViewModel
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.JokeService
import ru.pg13.mystudyproject.lessons.lesson8.models.BaseResourceManager
import ru.pg13.mystudyproject.lessons.lesson8.models.NoCachedJokes
import ru.pg13.mystudyproject.lessons.lesson8.models.NoConnection
import ru.pg13.mystudyproject.lessons.lesson8.models.ServiceUnavailable
import ru.pg13.mystudyproject.lessons.lesson9.BaseCloudDataSource
import ru.pg13.mystudyproject.lessons.lesson9.DB.BaseCachedDataSource

class MyApplication : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val cachedJoke = BaseCachedJoke()
        val cacheDataSource = BaseCachedDataSource(BaseRealmProvider())
        val resourceManager = BaseResourceManager(this)
        viewModel = ViewModel(
            BaseModel(
                cacheDataSource,
                CacheResultHandler(
                    cachedJoke,
                    cacheDataSource,
                    NoCachedJokes(resourceManager)
                ),
                CloudResultHandler(
                    cachedJoke,
                    BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                    NoConnection(resourceManager),
                    ServiceUnavailable(resourceManager)
                ),
                cachedJoke
            )
        )
    }
}