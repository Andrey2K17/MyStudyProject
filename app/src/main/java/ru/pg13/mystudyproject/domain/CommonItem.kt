package ru.pg13.mystudyproject.domain

import ru.pg13.mystudyproject.core.Mapper
import ru.pg13.mystudyproject.core.presentation.Failure
import ru.pg13.mystudyproject.presentation.BaseCommonUiModel
import ru.pg13.mystudyproject.presentation.CommonUiModel
import ru.pg13.mystudyproject.presentation.FailedJokeUiModel
import ru.pg13.mystudyproject.presentation.FavoriteCommonUiModel

sealed class CommonItem: Mapper<CommonUiModel> {

    class Success(
        private val text: String,
        private val favorite: Boolean
    ) : CommonItem() {
        override fun to(): CommonUiModel {
            return if (favorite) {
                FavoriteCommonUiModel(text)
            } else {
                BaseCommonUiModel(text)
            }
        }
    }

    class Failed(private val failure: Failure) : CommonItem() {
        override fun to(): CommonUiModel {
            return FailedJokeUiModel(failure.getMessage())
        }
    }
}