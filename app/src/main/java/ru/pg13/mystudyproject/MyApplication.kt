package ru.pg13.mystudyproject

import android.app.Application
import leakcanary.LeakCanary
import ru.pg13.mystudyproject.lessons.lesson5.Model
import ru.pg13.mystudyproject.lessons.lesson5.ViewModel
import ru.pg13.mystudyproject.lessons.lesson6.CacheDataSource

class MyApplication: Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()

        viewModel = ViewModel(Model(CacheDataSource(this)))
    }
}