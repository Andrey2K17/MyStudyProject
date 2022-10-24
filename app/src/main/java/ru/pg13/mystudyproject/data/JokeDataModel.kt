package ru.pg13.mystudyproject.data

import ru.pg13.mystudyproject.data.interfaces.ChangeJoke
import ru.pg13.mystudyproject.data.interfaces.ChangeJokeStatus
import ru.pg13.mystudyproject.domain.JokeDataModelMapper

class JokeDataModel(
     private val id: Int,
     private val text: String,
     private val cached: Boolean = false
) : ChangeJoke {

     fun <T>map(mapper: JokeDataModelMapper<T>) : T {
          return mapper.map(id, text, cached)
     }

     override suspend fun change(changeJokeStatus: ChangeJokeStatus): JokeDataModel =
          changeJokeStatus.addOrRemove(id, this)

     fun changeCached(cached: Boolean) = JokeDataModel(id, text, cached)
}