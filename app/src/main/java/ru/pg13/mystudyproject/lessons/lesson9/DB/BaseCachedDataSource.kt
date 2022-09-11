package ru.pg13.mystudyproject.lessons.lesson9.DB

import io.realm.Realm
import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
import ru.pg13.mystudyproject.lessons.lesson8.models.Value
import ru.pg13.mystudyproject.lessons.lesson9.CacheDataSource
import ru.pg13.mystudyproject.lessons.lesson9.JokeCachedCallback
import ru.pg13.mystudyproject.lessons.lesson9.JokeUiModel

class BaseCachedDataSource(private val realm: Realm) : CacheDataSource {
    override fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
        realm.let {
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
    }

    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
        realm.let {
            val jokes = it.where(JokeRealm::class.java).findAll()
            if (jokes.isEmpty())
                jokeCachedCallback.fail()
            else
                jokes.random().let { joke ->
                    jokeCachedCallback.provide(
                        Joke(
                            joke.type,
                            Value(joke.id, joke.text, listOf())
                        )
                    )
                }
        }
    }
}