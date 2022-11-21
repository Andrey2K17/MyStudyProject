package ru.pg13.mystudyproject.core.data.net

import ru.pg13.mystudyproject.data.CommonDataModel

interface ChangeCommonItem<E> {
    suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E>

    class Empty<E> : ChangeCommonItem<E> {
        override suspend fun change(changeStatus: ChangeStatus<E>): CommonDataModel<E> {
            throw IllegalStateException("empty change item called")
        }
    }
}