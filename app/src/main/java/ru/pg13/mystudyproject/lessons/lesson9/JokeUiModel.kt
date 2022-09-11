package ru.pg13.mystudyproject.lessons.lesson9

import androidx.annotation.DrawableRes
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.lessons.lesson8.DataCallback

class BaseJokeUiModel(text: String) : JokeUiModel(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_24
}

class FavoriteJokeUiModel(text: String) : JokeUiModel(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_24
}

class FailedJokeUiModel(text: String) : JokeUiModel(text) {
    override fun getIconResId() = 0
}

abstract class JokeUiModel(private val text: String) {

    protected fun text() = text

    @DrawableRes
    protected abstract fun getIconResId() : Int

    fun map(callback: DataCallback) = callback.run {
        provideText(text())
        provideIconRes(getIconResId())
    }
}