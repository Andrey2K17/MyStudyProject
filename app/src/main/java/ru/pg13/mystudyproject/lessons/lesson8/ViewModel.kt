package ru.pg13.mystudyproject.lessons.lesson8

import androidx.annotation.DrawableRes
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.Model
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.JokeCallback
import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

class ViewModel(private val model: Model) {

    private var dataCallback: DataCallback? = null

    fun init(callback: DataCallback) {
        this.dataCallback = callback
        model.init(object : JokeCallback {
            override fun provide(joke: JokeUiModel) {
                dataCallback?.let {
                    joke.map(it)
                }
            }

        })
    }

    private val jokeCallback = object : JokeCallback {
        override fun provide(data: JokeUiModel) {
            dataCallback?.let {
                data.map(it)
            }
        }

    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        dataCallback = null
        model.clear()
    }

    fun chooseFavorites(favorites: Boolean) {
        model.chooseDataSource(favorites)
    }

    fun changeJokeStatus() {
        model.changeJokeStatus(jokeCallback = jokeCallback)
    }
}

interface DataCallback {

    fun provideText(text: String)

    fun provideIconRes(@DrawableRes id: Int)
}