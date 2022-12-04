package ru.pg13.mystudyproject

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pg13.mystudyproject.core.data.cache.PersistentDataSource
import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.core.domain.FailureHandler
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

    val viewModelFactory by lazy {
        ViewModelsFactory(
            MainModule(persistentDataStore),
            JokesModule(failureHandler, realmProvider, retrofit),
            QuotesModule(failureHandler, realmProvider, retrofit)
        )
    }

    private lateinit var retrofit: Retrofit
    private lateinit var realmProvider: RealmProvider
    private lateinit var failureHandler: FailureHandler
    private lateinit var persistentDataStore: PersistentDataSource

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        realmProvider = BaseRealmProvider()
        failureHandler = FailureFactory(BaseResourceManager(this))
        persistentDataStore = PersistentDataSource.Base(this)
    }
}