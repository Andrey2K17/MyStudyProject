package ru.pg13.mystudyproject.core.data.net

import ru.pg13.mystudyproject.data.CommonDataModel

interface ChangeStatus<E> {
    suspend fun addOrRemove(id: E, model: CommonDataModel<E>): CommonDataModel<E>
}