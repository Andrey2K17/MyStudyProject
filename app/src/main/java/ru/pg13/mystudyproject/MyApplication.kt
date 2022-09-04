package ru.pg13.mystudyproject

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.pg13.mystudyproject.lessons.lesson8.BaseModel
import ru.pg13.mystudyproject.lessons.lesson8.ViewModel
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.JokeService
import ru.pg13.mystudyproject.lessons.lesson8.models.BaseResourceManager
import ru.pg13.mystudyproject.lessons.lesson8.models.TestModel
import ru.pg13.mystudyproject.lessons.lesson9.BaseCloudDataSource
import ru.pg13.mystudyproject.lessons.lesson9.TestCacheDataSource
import ru.pg13.mystudyproject.lessons.lesson9.TestCloudDataSource

class MyApplication : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        viewModel = ViewModel(
            BaseModel(
                TestCacheDataSource(),
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