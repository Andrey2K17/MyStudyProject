package ru.pg13.mystudyproject.presentation

import androidx.annotation.DrawableRes
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.core.presentation.Communication

class BaseCommonUiModel<E>(text: String) : CommonUiModel<E>(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_border_24
}

class FavoriteCommonUiModel<E>(private val id: E, text: String) : CommonUiModel<E>(text) {
    override fun getIconResId() = R.drawable.baseline_favorite_24
    override fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<E>) =
        listener.change(id)

    override fun matches(id: E) = this.id == id

    override fun same(model: CommonUiModel<E>): Boolean {
        return model is FavoriteCommonUiModel<E> && model.id == id
    }
}

class FailedCommonUiModel<E>(private val text: String) : CommonUiModel<E>(text) {
    override fun text() = text
    override fun getIconResId() = 0
    override fun show(communication: Communication) = communication.showState(
        State.Failed(text(), getIconResId())
    )
}

abstract class CommonUiModel<T>(private val text: String) {
    open fun same(model: CommonUiModel<T>) : Boolean = false
    protected open fun text() = text

    @DrawableRes
    protected abstract fun getIconResId() : Int

   open fun show(communication: Communication) = communication.showState(
        State.Initial(text(), getIconResId())
    )

    open fun change(listener: CommonDataRecyclerAdapter.FavoriteItemClickListener<T>) = Unit
    open fun matches(id: T): Boolean = false
    fun show(showText: ShowText) = showText.show(text)
}