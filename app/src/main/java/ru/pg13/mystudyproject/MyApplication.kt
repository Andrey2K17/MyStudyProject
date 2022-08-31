package ru.pg13.mystudyproject

import android.app.Application
import com.google.gson.Gson
import ru.pg13.mystudyproject.lessons.lesson8.BaseJokeService
import ru.pg13.mystudyproject.lessons.lesson8.BaseModel
import ru.pg13.mystudyproject.lessons.lesson8.ViewModel
import ru.pg13.mystudyproject.lessons.lesson8.models.BaseResourceManager

class MyApplication : Application() {

    lateinit var viewModel: ViewModel

    override fun onCreate() {
        super.onCreate()

        viewModel = ViewModel(BaseModel(BaseJokeService(Gson()), BaseResourceManager(this)))
    }
}