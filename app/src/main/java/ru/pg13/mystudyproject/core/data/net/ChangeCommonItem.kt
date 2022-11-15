package ru.pg13.mystudyproject.core.data.net

import ru.pg13.mystudyproject.data.CommonDataModel

interface ChangeCommonItem {
    suspend fun change(changeStatus: ChangeStatus): CommonDataModel

    class Empty : ChangeCommonItem {
        override suspend fun change(changeStatus: ChangeStatus): CommonDataModel {
            throw IllegalStateException("empty change item called")
        }
    }
}