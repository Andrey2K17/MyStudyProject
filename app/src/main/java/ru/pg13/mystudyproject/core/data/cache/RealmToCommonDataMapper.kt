package ru.pg13.mystudyproject.core.data.cache

import io.realm.RealmObject
import ru.pg13.mystudyproject.data.CommonDataModel

interface RealmToCommonDataMapper<T: RealmObject> {

    fun map(realmObject: T): CommonDataModel
}