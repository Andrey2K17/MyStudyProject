package ru.pg13.mystudyproject.lessons.lesson8.models

import ru.pg13.mystudyproject.lessons.lesson8.interfaces.Model
import ru.pg13.mystudyproject.lessons.lesson9.BaseJokeUiModel
import ru.pg13.mystudyproject.lessons.lesson9.FailedJokeUiModel
import ru.pg13.mystudyproject.lessons.lesson9.FavoriteJokeUiModel

//class TestModel(resourceManager: ResourceManager): Model {
//
//    private var callback: JokeCallback? = null
//    private var count = 0
//    private val noConnection = NoConnection(resourceManager)
//    private val serviceUnavailable = ServiceUnavailable(resourceManager)
//
//    override fun getJoke() {
//        Thread {
//            Thread.sleep(1000)
//            when(count) {
//                0 -> callback?.provide(BaseJokeUiModel("testText"))
//                1 -> callback?.provide(FavoriteJokeUiModel("favoriteJokeText"))
//                2 -> callback?.provide(FailedJokeUiModel(serviceUnavailable.getMessage()))
//            }
//            count++
//            if (count == 3) count = 0
//        }.start()
//    }
//
//    override fun init(callback: JokeCallback) {
//        this.callback = callback
//    }
//
//    override fun clear() {
//        callback = null
//    }
//
//    override fun changeJokeStatus(jokeCallback: JokeCallback) {
//
//    }
//
//    override fun chooseDataSource(favorites: Boolean) {
//        TODO("Not yet implemented")
//    }
//}