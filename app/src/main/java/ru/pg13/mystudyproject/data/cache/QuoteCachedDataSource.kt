package ru.pg13.mystudyproject.data.cache

import io.realm.Realm
import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.data.mapper.QuoteRealmMapper

class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper: QuoteRealmMapper,
    commonMapper: QuoteRealmToCommonMapper
) :
    BaseCachedDataSource<QuoteRealmModel, String>(realmProvider, mapper, commonMapper) {
    override val dbClass = QuoteRealmModel::class.java
    override fun findRealmObject(realm: Realm, id: String) =
        realm.where(dbClass).equalTo("id", id).findFirst()
}