package ru.pg13.mystudyproject.data.cache

import io.realm.Realm
import io.realm.RealmObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.pg13.mystudyproject.core.data.cache.CacheDataSource
import ru.pg13.mystudyproject.core.data.cache.RealmProvider
import ru.pg13.mystudyproject.core.data.cache.RealmToCommonDataMapper
import ru.pg13.mystudyproject.core.data.net.CommonDataModelMapper
import ru.pg13.mystudyproject.core.domain.NoCachedDataException
import ru.pg13.mystudyproject.data.CommonDataModel


abstract class BaseCachedDataSource<T : RealmObject, E>(
    private val realmProvider: RealmProvider,
    private val mapper: CommonDataModelMapper<T, E>,
    private val commonDataMapper: RealmToCommonDataMapper<T, E>
) : CacheDataSource<E> {

    protected abstract val dbClass: Class<T>

    protected abstract fun findRealmObject(realm: Realm, id: E): T?

    override suspend fun getData(): CommonDataModel<E> {
        realmProvider.provide().use {
            val list = it.where(dbClass).findAll()
            if (list.isEmpty())
                throw NoCachedDataException()
            else
                return commonDataMapper.map(list.random())
        }
    }



    override suspend fun addOrRemove(id: E, model: CommonDataModel<E>): CommonDataModel<E> =
        withContext(Dispatchers.IO) {
            realmProvider.provide().use {
                val itemRealm = findRealmObject(it, id)
                return@withContext if (itemRealm == null) {
                    it.executeTransactionAsync { transaction ->
                        val newData = model.map(mapper)
                        transaction.insert(newData)
                    }
                    model.changeCached(true)
                } else {
                    it.executeTransaction {
                        itemRealm.deleteFromRealm()
                    }
                    model.changeCached(false)
                }
            }
        }
}