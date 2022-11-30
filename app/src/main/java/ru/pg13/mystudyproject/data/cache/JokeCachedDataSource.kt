package ru.pg13.mystudyproject.data.cache

import io.realm.Realm
import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.data.mapper.JokeRealmMapper

class JokeCachedDataSource(
    realmProvider: RealmProvider,
    mapper: JokeRealmMapper,
    commonDataMapper: JokeRealmToCommonMapper
) : BaseCachedDataSource<JokeRealmModel, Int>(realmProvider, mapper, commonDataMapper) {
    override val dbClass = JokeRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: Int) =
        realm.where(dbClass).equalTo("id", id).findFirst()
}