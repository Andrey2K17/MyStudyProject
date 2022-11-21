package ru.pg13.mystudyproject.core.data.cache

import io.realm.RealmObject
import ru.pg13.mystudyproject.data.CommonDataModel

interface RealmToCommonDataMapper<T: RealmObject, E> {

    fun map(realmObject: T): CommonDataModel<E>
}