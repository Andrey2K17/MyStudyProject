package ru.pg13.mystudyproject.domain

import ru.pg13.mystudyproject.core.Mapper
import ru.pg13.mystudyproject.domain.interfaces.JokeFailure

sealed class Joke: Mapper<JokeUiModel> {

    class Success(
        private val text: String,
        private val favorite: Boolean
    ) : Joke() {
        override fun to(): JokeUiModel {
            return if (favorite) {
                FavoriteJokeUiModel(text)
            } else {
                BaseJokeUiModel(text)
            }
        }
    }

    class Failed(private val failure: JokeFailure) : Joke() {
        override fun to(): JokeUiModel {
            return FailedJokeUiModel(failure.getMessage())
        }
    }
}