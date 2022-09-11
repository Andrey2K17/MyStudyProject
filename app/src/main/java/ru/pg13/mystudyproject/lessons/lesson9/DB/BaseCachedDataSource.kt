package ru.pg13.mystudyproject.lessons.lesson9.DB

import io.realm.Realm
import ru.pg13.mystudyproject.lessons.lesson11.RealmProvider
import ru.pg13.mystudyproject.lessons.lesson11.Result
import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson8.models.Value
import ru.pg13.mystudyproject.lessons.lesson9.CacheDataSource
import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

class BaseCachedDataSource(private val realmProvider: RealmProvider) : CacheDataSource {
    override suspend fun addOrRemove(id: Int, joke: Joke): JokeUiModel  =
            Realm.getDefaultInstance().use {
                val jokeRealm = it.where(JokeRealm::class.java).equalTo("id", id).findFirst()
                return if (jokeRealm == null) {
                    val newJoke = joke.toJokeRealm()
                    it.executeTransactionAsync { transaction ->
                        transaction.insert(newJoke)
                    }
                    joke.toFavoriteJoke()
                } else {
                    it.executeTransaction {
                        jokeRealm.deleteFromRealm()
                    }
//                it.executeTransactionAsync {
//                    jokeRealm.deleteFromRealm()
//                }
                    joke.toBaseJoke()
                }
    }

    override suspend fun getJoke() : Result<Joke, Unit> {
        realmProvider.provide().use {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty())
                return Result.Error(Unit)
            else
                jokes.random().let { joke ->
                    return Result.Success(
                        Joke(
                            joke.type,
                            Value(joke.id, joke.text, listOf())
                        )
                    )
                }
        }
    }
}