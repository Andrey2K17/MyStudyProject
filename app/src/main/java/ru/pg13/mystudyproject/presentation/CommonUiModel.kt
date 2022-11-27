package ru.pg13.mystudyproject.presentation

import androidx.annotation.DrawableRes
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.core.presentation.Communication

class BaseCommonUiModel(text: String) : CommonUiModel(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_24
}

class FavoriteCommonUiModel(text: String) : CommonUiModel(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_24
}

class FailedJokeUiModel(private val text: String) : CommonUiModel(text) {
    override fun getIconResId() = 0
    override fun text() = text
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class CommonUiModel(private val text: String) {

    protected open fun text() = text

    @DrawableRes
    protected abstract fun getIconResId() : Int

   open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId())
    )

    fun show(showText: ShowText) = showText.show(text)
}