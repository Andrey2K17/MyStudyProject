package ru.pg13.mystudyproject.data.cache

import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.data.mapper.JokeRealmMapper

class JokeCachedDataSource(
    realmProvider: RealmProvider,
    mapper: JokeRealmMapper,
    commonMapper: JokeRealmToCommonMapper
) :
    BaseCachedDataSource<JokeRealmModel>(realmProvider, mapper, commonMapper) {
    override val dbClass = JokeRealmModel::class.java
}