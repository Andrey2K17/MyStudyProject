package ru.pg13.mystudyproject.lessons.lesson12

import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson9.ChangeJokeStatus
import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

class BaseCachedJoke: CachedJoke {
    private var cached: Joke? = null
    override fun saveJoke(joke: Joke) {
        cached = joke
    }

    override fun clear() {
        cached = null
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeUiModel? {
        return cached?.change(changeJokeStatus)
    }
}