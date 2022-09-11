package ru.pg13.mystudyproject

import android.app.Application
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pg13.mystudyproject.lessons.lesson8.BaseModel
import ru.pg13.mystudyproject.lessons.lesson8.ViewModel
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.JokeService
import ru.pg13.mystudyproject.lessons.lesson8.models.BaseResourceManager
import ru.pg13.mystudyproject.lessons.lesson8.models.TestModel
import ru.pg13.mystudyproject.lessons.lesson9.BaseCloudDataSource
import ru.pg13.mystudyproject.lessons.lesson9.DB.BaseCachedDataSource
import ru.pg13.mystudyproject.lessons.lesson9.TestCacheDataSource

class MyApplication : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viewModel = ViewModel(
            BaseModel(
                BaseCachedDataSource(Realm.getDefaultInstance()),
                BaseCloudDataSource(retrofit.create(JokeService::class.java)),
                BaseResourceManager(this))
            //TestModel(BaseResourceManager(this))
//            BaseModel(
//                retrofit.create(JokeService::class.java),
//                BaseResourceManager(this)
//            )
        )
    }
}