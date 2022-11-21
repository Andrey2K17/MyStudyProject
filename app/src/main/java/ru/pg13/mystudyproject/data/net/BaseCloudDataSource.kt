package ru.pg13.mystudyproject.data.net

import retrofit2.Call
import ru.pg13.mystudyproject.core.Mapper
import ru.pg13.mystudyproject.core.data.net.CloudDataSource
import ru.pg13.mystudyproject.core.domain.NoConnectionException
import ru.pg13.mystudyproject.core.domain.ServiceUnavailableException
import ru.pg13.mystudyproject.data.CommonDataModel
import java.net.UnknownHostException

abstract class BaseCloudDataSource<T : Mapper<CommonDataModel<E>>, E> : CloudDataSource<E> {
    protected abstract fun getServerModel() : Call<T>
    override suspend fun getData(): CommonDataModel<E> {
        return try {
            return getServerModel().execute().body()!!.to()
        } catch (e: Exception) {
            if (e is UnknownHostException) {
                throw NoConnectionException()
            } else {
                throw ServiceUnavailableException()
            }
        }
    }
}