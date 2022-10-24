package ru.pg13.mystudyproject.data

import ru.pg13.mystudyproject.data.interfaces.CloudDataSource
import ru.pg13.mystudyproject.data.interfaces.JokeService
import ru.pg13.mystudyproject.domain.NoConnectionException
import ru.pg13.mystudyproject.domain.ServiceUnavailableException
import java.net.UnknownHostException

class BaseCloudDataSource(private val service: JokeService) : CloudDataSource {

    override suspend fun getJoke(): JokeDataModel {
        return try {
            return service.getJoke().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                throw NoConnectionException()
            } else {
                throw ServiceUnavailableException()
            }
        }
    }
}