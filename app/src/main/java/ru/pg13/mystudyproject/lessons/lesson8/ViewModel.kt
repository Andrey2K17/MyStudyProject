package ru.pg13.mystudyproject.lessons.lesson8

import ru.pg13.mystudyproject.lessons.lesson8.interfaces.Model
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.ResultCallback
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeFailure
import ru.pg13.mystudyproject.lessons.lesson8.models.Joke

class ViewModel(private val model: Model) {

    private var callback: TextCallback? = null

    fun init(callback: TextCallback) {
        this.callback = callback
        model.init(object : ResultCallback {
            override fun provideSuccess(data: Joke) = callback.provideText(data.getJokeUi())

            override fun provideError(error: JokeFailure) = callback.provideText(error.getMessage())
        })
    }

    fun getJoke() {
        model.getJoke()
    }

    fun clear() {
        callback = null
        model.clear()
    }
}

interface TextCallback {

    fun provideText(text: String)
}