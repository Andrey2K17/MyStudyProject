package ru.pg13.mystudyproject.data

import ru.pg13.mystudyproject.data.interfaces.CachedJoke
import ru.pg13.mystudyproject.data.interfaces.ChangeJoke
import ru.pg13.mystudyproject.data.interfaces.ChangeJokeStatus

class BaseCachedJoke: CachedJoke {
    private var cached: ChangeJoke = ChangeJoke.Empty()

    override fun saveJoke(joke: JokeDataModel) {
        cached = joke
    }

    override fun clear() {
        cached = ChangeJoke.Empty()
    }

    override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel {
        return cached.change(changeJokeStatus)
    }
}