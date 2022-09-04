package ru.pg13.mystudyproject.lessons.lesson9

import androidx.annotation.DrawableRes
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.lessons.lesson8.DataCallback

class BaseJoke(text: String) : Joke(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_24
}

class FavoriteJoke(text: String) : Joke(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_24
}

class FailedJoke(text: String) : Joke(text) {
    override fun getIconResId() = 0
}

abstract class Joke(private val text: String) {

    protected fun getJokeUi() = text

    @DrawableRes
    protected abstract fun getIconResId() : Int

    fun map(callback: DataCallback) = callback.run {
        provideText(getJokeUi())
        provideIconRes(getIconResId())
    }
}