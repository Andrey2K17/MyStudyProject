package ru.pg13.mystudyproject.lessons.lesson9

import ru.pg13.mystudyproject.lessons.lesson11.Result
import ru.pg13.mystudyproject.lessons.lesson8.interfaces.JokeService
import ru.pg13.mystudyproject.lessons.lesson8.models.JokeServerModel
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService): CloudDataSource {
    override suspend fun getJoke(): Result<JokeServerModel, ErrorType> {
        return try {
            val result: JokeServerModel = service.getJoke().execute().body()!!
            Result.Success(result)
        } catch (e: Exception) {
            val errorType = if (e is UnknownHostException)
                ErrorType.NO_CONNECTION
            else
                ErrorType.SERVICE_UNAVAILABLE
            return Result.Error(errorType)
        }
    }

}