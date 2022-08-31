package ru.pg13.mystudyproject.lessons.lesson8

import ru.pg13.mystudyproject.lessons.lesson8.interfaces.*
import ru.pg13.mystudyproject.lessons.lesson8.models.*

class BaseModel(
    private val service: JokeService,
    private val resourceManager: BaseResourceManager
) : Model {
    private var callback: ResultCallback? = null
    private val noConnection by lazy { NoConnection(resourceManager) }
    private val serviceUnavailable by lazy { ServiceUnavailable(resourceManager) }

    override fun getJoke() {
        service.getJoke(object : ServiceCallback {
            override fun returnSuccess(data: JokeDTO) {
                callback?.provideSuccess(data.toJoke())
            }

            override fun returnError(type: ErrorType) {
                when (type) {
                    ErrorType.OTHER -> callback?.provideError(serviceUnavailable)
                    ErrorType.NO_CONNECTION -> callback?.provideError(noConnection)
                }
            }
        })
    }

    override fun init(callback: ResultCallback) {
        this.callback = callback
    }

    override fun clear() {
        callback = null
    }
}