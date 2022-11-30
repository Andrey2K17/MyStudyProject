package ru.pg13.mystudyproject.domain

import ru.pg13.mystudyproject.core.Mapper
import ru.pg13.mystudyproject.core.presentation.Failure
import ru.pg13.mystudyproject.presentation.BaseCommonUiModel
import ru.pg13.mystudyproject.presentation.CommonUiModel
import ru.pg13.mystudyproject.presentation.FailedCommonUiModel
import ru.pg13.mystudyproject.presentation.FavoriteCommonUiModel

sealed class CommonItem<E> : Mapper<CommonUiModel<E>> {
    class Success<E>(
        private val id: E,
        private val text: String,
        private val favorite: Boolean
    ) : CommonItem<E>() {
        override fun to() = if (favorite) {
            FavoriteCommonUiModel(id, text)
        } else {
            BaseCommonUiModel(text)
        }
    }

    class Failed<E>(private val failure: Failure) : CommonItem<E>() {
        override fun to(): CommonUiModel<E> = FailedCommonUiModel(failure.getMessage())
    }
}