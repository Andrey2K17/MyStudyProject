package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson8.models.Joke
//
//class TestCacheDataSource: CacheDataSource {
//
//    private val list = ArrayList<Pair<Int, Joke>>()
//
//    override fun addOrRemove(id: Int, joke: Joke): JokeUiModel {
//        val found = list.find { it.first == id }
//        return if (found != null) {
//            val joke = found.second.toBaseJoke()
//            list.remove(found)
//            joke
//        } else {
//            list.add(Pair(id, joke))
//            joke.toFavoriteJoke()
//        }
//    }
//
//    override fun getJoke(jokeCachedCallback: JokeCachedCallback) {
//        if (list.isEmpty())
//            jokeCachedCallback.fail()
//        else
//            jokeCachedCallback.provide(list.random().second)
//    }
//}