package ru.pg13.mystudyproject.domain

import androidx.annotation.DrawableRes
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.State
import ru.pg13.mystudyproject.domain.interfaces.Communication

class BaseJokeUiModel(text: String) : JokeUiModel(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_24
}

class FavoriteJokeUiModel(text: String) : JokeUiModel(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_24
}

class FailedJokeUiModel(private val text: String) : JokeUiModel(text) {
    override fun getIconResId() = 0
    override fun text() = text
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class JokeUiModel(private val text: String) {

    protected open fun text() = text

    @DrawableRes
    protected abstract fun getIconResId() : Int

   open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId())
    )
}