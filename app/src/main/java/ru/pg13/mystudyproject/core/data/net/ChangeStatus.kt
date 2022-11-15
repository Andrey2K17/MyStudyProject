package ru.pg13.mystudyproject.core.data.net

import ru.pg13.mystudyproject.data.CommonDataModel

interface ChangeStatus {
    suspend fun addOrRemove(id: Int, model: CommonDataModel): CommonDataModel
}