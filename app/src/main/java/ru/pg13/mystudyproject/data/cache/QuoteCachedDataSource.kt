package ru.pg13.mystudyproject.data.cache

import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.data.mapper.QuoteRealmMapper

class QuoteCachedDataSource(
    realmProvider: RealmProvider,
    mapper: QuoteRealmMapper,
    commonMapper: QuoteRealmToCommonMapper
) :
    BaseCachedDataSource<QuoteRealmModel>(realmProvider, mapper, commonMapper) {
    override val dbClass = QuoteRealmModel::class.java
}